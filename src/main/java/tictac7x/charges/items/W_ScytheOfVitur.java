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
import tictac7x.charges.item.ChargedItem;
import tictac7x.charges.item.triggers.OnChatMessage;
import tictac7x.charges.item.triggers.OnHitsplatApplied;
import tictac7x.charges.item.triggers.TriggerBase;
import tictac7x.charges.item.triggers.TriggerItem;
import tictac7x.charges.store.HitsplatTarget;
import tictac7x.charges.store.Store;

public class W_ScytheOfVitur extends ChargedItem
{
	private int hitsplatTick;

	public W_ScytheOfVitur(
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
	)
	{
		super(TicTac7xChargesImprovedConfig.scythe_of_vitur, ItemID.SCYTHE_OF_VITUR, client, clientThread, configManager, itemManager, infoBoxManager, chatMessageManager, notifier, config, store, gson);

		this.items = new TriggerItem[]{
			new TriggerItem(ItemID.SCYTHE_OF_VITUR),
			new TriggerItem(ItemID.SCYTHE_OF_VITUR_UNCHARGED).fixedCharges(0),
			new TriggerItem(ItemID.HOLY_SCYTHE_OF_VITUR),
			new TriggerItem(ItemID.HOLY_SCYTHE_OF_VITUR_UNCHARGED).fixedCharges(0),
			new TriggerItem(ItemID.SANGUINE_SCYTHE_OF_VITUR),
			new TriggerItem(ItemID.SANGUINE_SCYTHE_OF_VITUR_UNCHARGED).fixedCharges(0),
		};

		this.triggers = new TriggerBase[]{
			// Check.
			new OnChatMessage("Your (Holy s|Sanguine s|S)cythe of vitur has (?<charges>.+) charges (remaining|left).").setDynamicallyCharges(),

			// Charge partially full.
			new OnChatMessage("You apply an additional .+ charges to your (Holy s|Sanguine s|S)cythe of vitur. It now has (?<charges>.+) charges in total.").setDynamicallyCharges(),

			// Charge empty.
			new OnChatMessage("You apply (?<charges>.+) charges to your (Holy s|Sanguine s|S)cythe of vitur.").setDynamicallyCharges(),

			// Attack.
			// `OnHitsplatApplied` is triggered multiple times per attack, so we ensure that the charge is only decreased once per attack.
			new OnHitsplatApplied(HitsplatTarget.ENEMY).moreThanZeroDamage().isEquipped().consumer(() -> {
				final int tickCount = client.getTickCount();
				if (tickCount == this.hitsplatTick) return;
				decreaseCharges(1);
				this.hitsplatTick = tickCount;
			}),
		};
	}
}
