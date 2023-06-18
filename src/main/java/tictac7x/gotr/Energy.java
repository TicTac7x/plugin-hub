package tictac7x.gotr;

import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Energy {
    private final Client client;
    private final ConfigManager configs;
    private final TicTac7xGotrImprovedConfig config;

    private final Pattern regex_check = Pattern.compile("You have (?<catalytic>.+) catalytic energy and (?<elemental>.+) elemental energy.");
    private final Pattern regex_catalytic_energy = Pattern.compile("Catalytic<br>Energy: (?<catalytic>.+)");
    private final Pattern regex_elemental_energy = Pattern.compile("Elemental<br>Energy: (?<elemental>.+)");

    private double catalytic_energy;
    private double elemental_energy;

    public Energy(final Client client, final ConfigManager configs, final TicTac7xGotrImprovedConfig config) {
        this.client = client;
        this.configs = configs;
        this.config = config;
    }

    public double getElementalEnergy() {
        return config.getElementalEnergy() + elemental_energy;
    }

    public double getCatalyticEnergy() {
        return config.getCatalyticEnergy() + catalytic_energy;
    }

    public void onChatMessage(final ChatMessage message) {
        if (message.getType() != ChatMessageType.MESBOX) return;

        final Matcher matcher = regex_check.matcher(message.getMessage());
        if (!matcher.find()) return;

        configs.setConfiguration(TicTac7xGotrImprovedConfig.group, TicTac7xGotrImprovedConfig.energy_catalytic, Integer.parseInt(matcher.group("catalytic")));
        configs.setConfiguration(TicTac7xGotrImprovedConfig.group, TicTac7xGotrImprovedConfig.energy_elemental, Integer.parseInt(matcher.group("elemental")));
    }

    public void onGameTick() {
        final Widget widget_catalytic_energy = client.getWidget(746, 24);
        final Widget widget_elemental_energy = client.getWidget(746, 21);

        if (widget_catalytic_energy != null) {
            final Matcher matcher = regex_catalytic_energy.matcher(widget_catalytic_energy.getText());
            if (matcher.find()) {
                catalytic_energy = Integer.parseInt(matcher.group("catalytic")) / 100.0;
            }
        }

        if (widget_elemental_energy != null) {
            final Matcher matcher = regex_elemental_energy.matcher(widget_elemental_energy.getText());
            if (matcher.find()) {
                elemental_energy = Integer.parseInt(matcher.group("elemental")) / 100.0;
            }
        }
    }
}