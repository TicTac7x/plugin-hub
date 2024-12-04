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
import tictac7x.charges.item.triggers.OnResetDaily;
import tictac7x.charges.item.triggers.OnWidgetLoaded;
import tictac7x.charges.item.triggers.TriggerBase;
import tictac7x.charges.item.triggers.TriggerItem;
import tictac7x.charges.store.Store;

public class C_MagicCape extends ChargedItem {
    public C_MagicCape(
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
        super(ChargesImprovedConfig.magic_cape, ItemID.MAGIC_CAPE, client, client_thread, configs, items, infoboxes, chat_messages, notifier, config, store, gson);

        this.items = new TriggerItem[]{
            new TriggerItem(ItemID.MAGIC_CAPE),
            new TriggerItem(ItemID.MAGIC_CAPET)
        };

        this.triggers = new TriggerBase[] {
            // After spellbook swap.
            new OnChatMessage("You have changed your spellbook (?<used>.+)/(?<total>.+) times today.").useDifference(),

            // Spellbook swap widget.
            new OnWidgetLoaded(219, 1, 0).text("Choose spellbook: \\((?<charges>.+)/5 left\\)").setDynamically(),

            // Daily reset.
            new OnResetDaily().setFixedCharges(5),
        };
    }
}
