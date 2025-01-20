package tictac7x.charges.items.barrows;

import com.google.gson.Gson;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.Notifier;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.item.ChargedItem;
import tictac7x.charges.item.triggers.TriggerItem;
import tictac7x.charges.store.Store;

public class VeracsBrassard extends ChargedItem {
    public VeracsBrassard(
        final Client client,
        final ClientThread clientThread,
        final ConfigManager configManager,
        final ItemManager itemManager,
        final InfoBoxManager infoBoxManager,
        final ChatMessageManager chatMessageManager,
        final Notifier notifier,
        final ChargesImprovedConfig config,
        final Store store,
        final Gson gson
    ) {
        super(ChargesImprovedConfig.barrows_gear, ItemID.VERACS_BRASSARD, client, clientThread, configManager, itemManager, infoBoxManager, chatMessageManager, notifier, config, store, gson);
        this.items = new TriggerItem[]{
            new TriggerItem(ItemID.VERACS_BRASSARD).fixedCharges(100),
            new TriggerItem(ItemID.VERACS_BRASSARD_100).fixedCharges(100),
            new TriggerItem(ItemID.VERACS_BRASSARD_75).fixedCharges(75),
            new TriggerItem(ItemID.VERACS_BRASSARD_50).fixedCharges(50),
            new TriggerItem(ItemID.VERACS_BRASSARD_25).fixedCharges(25),
            new TriggerItem(ItemID.VERACS_BRASSARD_0).fixedCharges(0)
        };
    }
}