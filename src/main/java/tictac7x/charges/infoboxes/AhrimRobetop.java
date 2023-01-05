package tictac7x.charges.infoboxes;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import tictac7x.charges.ChargedItemInfoBox;
import tictac7x.charges.triggers.TriggerItem;

public class AhrimRobetop extends ChargedItemInfoBox {
    public AhrimRobetop(final Client client, final ClientThread client_thread, final ConfigManager configs, final ItemManager items, final Plugin plugin) {
        super(ItemID.AHRIMS_ROBETOP, client, client_thread, configs, items, plugin);
        this.item_ids_to_render = new int[]{
                ItemID.AHRIMS_ROBETOP,
                ItemID.AHRIMS_ROBETOP_100,
                ItemID.AHRIMS_ROBETOP_75,
                ItemID.AHRIMS_ROBETOP_50,
                ItemID.AHRIMS_ROBETOP_25,
                ItemID.AHRIMS_ROBETOP_0
        };
        this.triggers_items = new TriggerItem[]{
            new TriggerItem(ItemID.AHRIMS_ROBETOP, 100),
            new TriggerItem(ItemID.AHRIMS_ROBETOP_100, 100),
            new TriggerItem(ItemID.AHRIMS_ROBETOP_75, 75),
            new TriggerItem(ItemID.AHRIMS_ROBETOP_50, 50),
            new TriggerItem(ItemID.AHRIMS_ROBETOP_25, 25),
            new TriggerItem(ItemID.AHRIMS_ROBETOP_0, 0)
        };
    }
}