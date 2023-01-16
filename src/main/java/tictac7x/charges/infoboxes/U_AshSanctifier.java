package tictac7x.charges.infoboxes;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.ChargedItemInfoBox;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.triggers.TriggerAnimation;
import tictac7x.charges.triggers.TriggerChatMessage;
import tictac7x.charges.triggers.TriggerItem;
import tictac7x.charges.triggers.TriggerWidget;

import javax.annotation.Nullable;

public class U_AshSanctifier extends ChargedItemInfoBox {
    @Nullable private String tooltip_extra;

    public U_AshSanctifier(final Client client, final ClientThread client_thread, final ConfigManager configs, final ItemManager items, final InfoBoxManager infoboxes, final Plugin plugin) {
        super(ItemID.ASH_SANCTIFIER, client, client_thread, configs, items, infoboxes, plugin);
        this.config_key = ChargesImprovedConfig.ash_sanctifier;
        this.triggers_items = new TriggerItem[]{
            new TriggerItem(ItemID.ASH_SANCTIFIER),
        };
        this.triggers_chat_messages = new TriggerChatMessage[]{
            new TriggerChatMessage("Your ash sanctifier has (?<charges>.+) charges? left."),
            new TriggerChatMessage("The ash sanctifier has (?<charges>.+) charges?. It is active and ready to scatter ashes."),
        };
    }
}
