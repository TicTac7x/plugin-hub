package tictac7x.charges.infoboxes;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import tictac7x.charges.ChargedItemInfoBox;
import tictac7x.charges.ChargesImprovedConfig;
import tictac7x.charges.ChargesItem;
import tictac7x.charges.triggers.TriggerAnimation;
import tictac7x.charges.triggers.TriggerChatMessage;
import tictac7x.charges.triggers.TriggerItem;
import tictac7x.charges.triggers.TriggerWidget;

public class W_SanguinestiStaff extends ChargedItemInfoBox {
    public W_SanguinestiStaff(final Client client, final ClientThread client_thread, final ConfigManager configs, final ItemManager items, final InfoBoxManager infoboxes, final ChatMessageManager chat_messages, final ChargesImprovedConfig config, final Plugin plugin) {
        super(ChargesItem.SANGUINESTI_STAFF, ItemID.SANGUINESTI_STAFF, client, client_thread, configs, items, infoboxes, chat_messages, config, plugin);
        this.config_key = ChargesImprovedConfig.sanguinesti_staff;
        this.triggers_items = new TriggerItem[]{
                new TriggerItem(ItemID.SANGUINESTI_STAFF),
                new TriggerItem(ItemID.SANGUINESTI_STAFF_UNCHARGED)
        };
        this.triggers_chat_messages = new TriggerChatMessage[]{
                new TriggerChatMessage("Your Sanguinesti staff has (?<charges>.+) charges remaining.").onItemClick(),
        };
        this.triggers_widgets = new TriggerWidget[]{
                new TriggerWidget("You apply an additional .* charges? to the Sanguinesti staff. It now has (?<charges>.+) in total.")
        };
    }
}
