package tictac7x.balloon;

import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import lombok.extern.slf4j.Slf4j;
import com.google.inject.Provides;
import net.runelite.client.plugins.Plugin;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.client.game.ItemManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

@Slf4j
@PluginDescriptor(
	name = "Balloon Transport System",
	description = "Show amount of logs stored in the balloon transport system storages.",
	tags = { "balloon", "transport", "logs", "storage" }
)
public class BalloonPlugin extends Plugin {
	@Inject
	private Client client;

	@Inject
	private OverlayManager overlays;

	@Inject
	private ConfigManager configs;

	@Inject
	private InfoBoxManager infoboxes;

	@Inject
	private ClientThread client_thread;

	@Inject
	private ItemManager items;

	@Inject
	private BalloonConfig config;

	private Storage storage;
	private BalloonOverlayLogs overlay_logs;
	private BalloonInfoboxLogs infobox_logs;
	private BalloonInfoboxLogs infobox_logs_oak;
	private BalloonInfoboxLogs infobox_logs_willow;
	private BalloonInfoboxLogs infobox_logs_yew;
	private BalloonInfoboxLogs infobox_logs_magic;

	@Override
	protected void startUp() {
		if (overlay_logs == null) {
			storage = new Storage(config, client, configs);
			overlay_logs = new BalloonOverlayLogs(config, configs, items, storage);

			infobox_logs = new BalloonInfoboxLogs(
				"logs",
				items.getImage(ItemID.LOGS),
				() -> showInfobox(Storage.Logs.LOGS),
				() -> storage.getLogsCount(Storage.Logs.LOGS),
				"Entrana / Taverley",
				this
			);

			infobox_logs_oak = new BalloonInfoboxLogs(
				"logs_oak",
				items.getImage(ItemID.OAK_LOGS),
				() -> showInfobox(Storage.Logs.LOGS_OAK),
				() -> storage.getLogsCount(Storage.Logs.LOGS_OAK),
				"Crafting Guild",
				this);

			infobox_logs_willow = new BalloonInfoboxLogs(
				"logs_willow",
				items.getImage(ItemID.WILLOW_LOGS),
				() -> showInfobox(Storage.Logs.LOGS_WILLOW),
				() -> storage.getLogsCount(Storage.Logs.LOGS_WILLOW),
				"Varrock",
				this);

			infobox_logs_yew = new BalloonInfoboxLogs(
				"logs_yew",
				items.getImage(ItemID.YEW_LOGS),
				() -> showInfobox(Storage.Logs.LOGS_YEW),
				() -> storage.getLogsCount(Storage.Logs.LOGS_YEW),
				"Castle Wars",
				this);

			infobox_logs_magic = new BalloonInfoboxLogs(
				"logs_magic",
				items.getImage(ItemID.MAGIC_LOGS),
				() -> showInfobox(Storage.Logs.LOGS_MAGIC),
				() -> storage.getLogsCount(Storage.Logs.LOGS_MAGIC),
				"Grand Tree",
				this);
		}

		overlays.add(overlay_logs);
		infoboxes.addInfoBox(infobox_logs);
		infoboxes.addInfoBox(infobox_logs_oak);
		infoboxes.addInfoBox(infobox_logs_willow);
		infoboxes.addInfoBox(infobox_logs_yew);
		infoboxes.addInfoBox(infobox_logs_magic);
	}

	@Override
	protected void shutDown() {
		overlays.remove(overlay_logs);
		infoboxes.removeInfoBox(infobox_logs);
		infoboxes.removeInfoBox(infobox_logs_oak);
		infoboxes.removeInfoBox(infobox_logs_willow);
		infoboxes.removeInfoBox(infobox_logs_yew);
		infoboxes.removeInfoBox(infobox_logs_magic);
	}

	@Subscribe
	public void onChatMessage(final ChatMessage event) {
		client_thread.invokeLater(() -> storage.onChatMessage(event));
	}

	@Subscribe
	public void onWidgetLoaded(final WidgetLoaded event) {
		client_thread.invokeLater(() -> storage.onWidgetLoaded(event));
	}

	@Provides
	BalloonConfig provideConfig(ConfigManager configManager) {
		return configManager.getConfig(BalloonConfig.class);
	}

	private boolean showInfobox(final Storage.Logs logs) {
		return config.getStyle() == BalloonConfig.style.INFOBOXES && storage.showLogs(logs);
	}
}
