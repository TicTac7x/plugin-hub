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
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.item.ChargedItem;
import tictac7x.charges.item.triggers.OnChatMessage;
import tictac7x.charges.item.triggers.OnGraphicChanged;
import tictac7x.charges.item.triggers.TriggerBase;
import tictac7x.charges.item.triggers.TriggerItem;
import tictac7x.charges.store.Store;

public class U_GricollersCan extends ChargedItem {
    public U_GricollersCan(
        final Client client,
        final ClientThread client_thread,
        final ConfigManager configs,
        final ItemManager items,
        final InfoBoxManager infoboxes,
        final ChatMessageManager chat_messages,
        final Notifier notifier,
        final ChargesImprovedConfig config,
        final Store store,
        final Gson gson
    ) {
        super(ChargesImprovedConfig.gricollers_can, ItemID.GRICOLLERS_CAN, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, gson);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemID.GRICOLLERS_CAN),
        };

        this.triggers = new TriggerBase[] {
            // Check.
            new OnChatMessage("Watering can charges remaining: (?<charges>.+)%").setDynamicallyCharges().onItemClick(),

            // Water inventory item.
            new OnChatMessage("You water").onItemClick().decreaseCharges(1),

            // Fill.
            new OnChatMessage("You fill the watering can").onItemClick().setFixedCharges(1000),

            // Water.
            new OnGraphicChanged(410).decreaseCharges(1),
        };
    }
}