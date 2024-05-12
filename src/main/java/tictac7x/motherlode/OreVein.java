package tictac7x.motherlode;

import net.runelite.api.WallObject;

import java.awt.*;

public class OreVein {
    public final int x;
    public final int y;
    private boolean isDepleted;
    private boolean isMined = false;
    private int gameTick = 0;
    public final Sector sector;

    private final TicTac7xMotherlodeConfig config;
    private final int RESPAWN_TIME_GAMETICKS = 100;
    private final int DESPAWN_TIME_DOWNSTAIRS_GAMETICKS = 42;
    private final int DESPAWN_TIME_UPPERFLOOR_GAMETICKS = 63;
    private static final int[] ORE_VEINS_IDS = new int[]{ 26661, 26662, 26663, 26664};
    private static final int[] DEPLETED_ORE_VEINS_IDS = new int[]{ 26665, 26666, 26667, 26668 };

    public OreVein(final int x, final int y, final boolean isDepleted, final TicTac7xMotherlodeConfig config) {
        this.x = x;
        this.y = y;
        this.isDepleted = isDepleted;
        this.sector = Motherlode.getSectors(x, y, false).get(0);
        this.config = config;
    }

    public void setDepleted(final boolean isDepleted) {
        if (this.isDepleted == isDepleted) return;

        this.isDepleted = isDepleted;
        this.gameTick = 0;
        this.isMined = false;
    }

    public void setMined() {
        if (isMined || isDepleted) return;

        isMined = true;
        gameTick = 0;
    }

    public void onGameTick() {
        if (isMined || isDepleted) {
            gameTick++;
        }
    }

    public float getPieProgress() {
        return isDepleted
            ? Math.max(0 - (float) gameTick / RESPAWN_TIME_GAMETICKS, -1)
            : Math.max(1 - (float) gameTick / (sector == Sector.DOWNSTAIRS ? DESPAWN_TIME_DOWNSTAIRS_GAMETICKS : DESPAWN_TIME_UPPERFLOOR_GAMETICKS), 0);
    }

    public Color getPieColor() {
        return isDepleted ? config.getOreVeinsDepletedColor() : config.getOreVeinsColor();
    }

    public static boolean isOreVein(final WallObject wallObject) {
        for (final int oreVeinId : ORE_VEINS_IDS) {
            if (wallObject.getId() == oreVeinId) return true;
        }

        return false;
    }

    public static boolean isDepletedOreVein(final WallObject wallObject) {
        for (final int depletedOreVeinId : DEPLETED_ORE_VEINS_IDS) {
            if (wallObject.getId() == depletedOreVeinId) return true;
        }

        return false;
    }
}
