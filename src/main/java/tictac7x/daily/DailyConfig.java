package tictac7x.daily;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.util.Date;

@ConfigGroup(DailyConfig.group)
public interface DailyConfig extends Config {
    String group = "tictac7x-daily";

    String ogre_arrows_id = "ogre_arrows";
    String ogre_arrows_name = "Rantz ogre arrows";
    String ogre_arrows_description = "Reminds you to collect ogre arrows from Rantz near Feldip Hills cave.";

    @ConfigItem(
        keyName = ogre_arrows_id,
        name = ogre_arrows_name,
        description = ogre_arrows_description
    ) default boolean showOgreArrows() { return true; }

    String battlestaves_id = "battlestaves";
    String battlestaves_name = "Zaff's battlestaves";
    String battlestaves_description = "Reminds you to buy battlestaves from Zaff at Varrock.";

    @ConfigItem(
        keyName = battlestaves_id,
        name = battlestaves_name,
        description = battlestaves_description
    ) default boolean showBattlestaves() { return true; }

    String buckets_of_sand_id = "buckets_of_sand";
    String buckets_of_sand_name = "Bert's buckets of sand";
    String buckets_of_sand_description = "Reminds you to collect 84 buckets of sand from Bert at Yanille.";

    @ConfigItem(
        keyName = buckets_of_sand_id,
        name = buckets_of_sand_name,
        description = buckets_of_sand_description
    ) default boolean showBucketsOfSand() { return true; }

    String pure_essence_id = "pure_essence";
    String pure_essence_name = "Wizard Cromperty's pure essence";
    String pure_essence_description = "Reminds you to collect pure essence from Wizard Cromperty at East-Ardougne.";

    @ConfigItem(
        keyName = pure_essence_id,
        name = pure_essence_name,
        description = pure_essence_description
    ) default boolean showPureEssence() { return true; }

    String buckets_of_slime_id = "buckets_of_slime";
    String buckets_of_slime_name = "Robin's buckets of slime and bonemeal";
    String buckets_of_slime_description = "Reminds you to exchange bones for buckets of slime and bonemeal from Robin at Port Phasmatys.";

    @ConfigItem(
        keyName = buckets_of_slime_id,
        name = buckets_of_slime_name,
        description = buckets_of_slime_description
    ) default boolean showBucketsOfSlime() { return true; }

    String miscellania_id = "miscellania";
    String miscellania_name = "Miscellania favor";
    String miscellania_description = "Reminds you to keep favor of Miscellania people at 100%.";

    @ConfigItem(
        keyName = miscellania_id,
        name = miscellania_name,
        description = miscellania_description
    ) default boolean showMiscellania() { return true; }

    String miscellania_favor = "miscellania_favor";

    @ConfigItem(
        keyName = miscellania_favor,
        name = miscellania_favor,
        description = miscellania_favor,
        hidden = false
    ) default int getMiscellaniaFavor() { return 0; }

    String miscellania_favor_date = "miscellania_favor_date";

    @ConfigItem(
        keyName = miscellania_favor_date,
        name = miscellania_favor_date,
        description = miscellania_favor_date,
        hidden = false
    ) default String getMiscellaniaFavorDate() { return null; }
}
