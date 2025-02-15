package tictac7x.charges.items;

import com.google.gson.Gson;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.TicTac7xChargesImprovedConfig;
import tictac7x.charges.item.ChargedItemWithStatus;
import tictac7x.charges.item.triggers.*;
import tictac7x.charges.store.Charges;
import tictac7x.charges.store.ItemActivity;
import tictac7x.charges.store.Store;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;

public class J_EscapeCrystal extends ChargedItemWithStatus {
    private Instant instant = Instant.now();
    private boolean alertedAboutActivation = false;

    public J_EscapeCrystal(
        final Client client,
        final ClientThread clientThread,
        final ConfigManager configManager,
        final ItemManager itemManager,
        final InfoBoxManager infoBoxManager,
        final ChatMessageManager chatMessageManager,
        final Notifier notifier,
        final TicTac7xChargesImprovedConfig config,
        final Store store,
        final Gson gson
    ) {
        super(TicTac7xChargesImprovedConfig.escape_crystal, client, clientThread, configManager, itemManager, infoBoxManager, chatMessageManager, notifier, config, store, gson);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemID.ESCAPE_CRYSTAL).quantityCharges().hideOverlay(),
        };

        this.triggers = new TriggerBase[]{
            // Activate / Deactivate.
            new OnVarbitChanged(14838).varbitValueConsumer(value -> {
                if (value == 1) {
                    activate();
                } else {
                    deactivate();
                }
            }),

            // Inactivity period from dialog when changed inactivity period.
            new OnChatMessage("The inactivity period for auto-activation (remains unchanged at|is now) (?<seconds>.+?)s.*").matcherConsumer(matcher -> {
                configManager.setConfiguration(TicTac7xChargesImprovedConfig.group, TicTac7xChargesImprovedConfig.escape_crystal_inactivity_period, matcher.group("seconds"));
            }),

            // Inactivity period from game message when activating.
            new OnChatMessage("Your escape crystals will now auto-activate if you take damage after a (?<seconds>.+?) seconds.*").matcherConsumer(matcher -> {
                configManager.setConfiguration(TicTac7xChargesImprovedConfig.group, TicTac7xChargesImprovedConfig.escape_crystal_inactivity_period, matcher.group("seconds"));
            }),

            // Inactivity period from widget.
            new OnWidgetLoaded(219, 1, 3).text("Set auto-activation inactivity period \\(in seconds\\)\\(current: (?<seconds>.+?)s\\)").matcherConsumer(matcher -> {
                configManager.setConfiguration(TicTac7xChargesImprovedConfig.group, TicTac7xChargesImprovedConfig.escape_crystal_inactivity_period, matcher.group("seconds"));
            }),

            // Keyboard or mouse action resets idle timer.
            new OnUserAction().consumer(() -> {
                resetIdleTimer();
            })
        };
    }

    private boolean isInCombat() {
        return client.getLocalPlayer().getHealthScale() != -1;
    }

    private void resetIdleTimer() {
        instant = Instant.now();
        alertedAboutActivation = false;
    }

    private long getSecondsRemainingUntilActivation() {
        return Math.max(0, config.getEscapeCrystalInactivityPeriod() - Duration.between(instant, Instant.now()).toMillis() / 1000);
    }

    private boolean isAboutToActivate() {
        return isActivated() && isInCombat() && (getSecondsRemainingUntilActivation() <= config.getEscapeCrystalTimeRemainingWarning());
    }

    @Override
    public Color getTextColor(final int itemId) {
        return isAboutToActivate() ? Color.YELLOW : super.getTextColor(itemId);
    }

    @Override
    public String getCharges(final int itemId) {
        if (config.getEscapeCrystalStatus() == ItemActivity.DEACTIVATED || (!inInventory() && !inEquipment())) { return "∞"; }
        if (config.getEscapeCrystalInactivityPeriod() == Charges.UNKNOWN) { return "?"; }

        final long secondsRemainingUntilActivation = getSecondsRemainingUntilActivation();
        if (!alertedAboutActivation && isAboutToActivate()) {
            alertedAboutActivation = true;
            notifier.notify("Escape crystal is activating in " + secondsRemainingUntilActivation + " seconds.");
        }

        return secondsRemainingUntilActivation / 60 + ":" + String.format("%02d", secondsRemainingUntilActivation % 60);
    }
}
