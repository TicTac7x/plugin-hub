package tictac7x;

import net.runelite.api.Client;
import net.runelite.api.DecorativeObject;
import net.runelite.api.GameObject;
import net.runelite.api.GroundObject;
import net.runelite.api.Scene;
import net.runelite.api.Tile;
import net.runelite.api.TileObject;
import net.runelite.api.WallObject;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.ProgressPieComponent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

public abstract class Overlay extends OverlayPanel {
    public static final int clickbox_stroke_width = 1;
    public static final Color color_red    = new Color(255, 0, 0);
    public static final Color color_green  = new Color(0, 255, 0);
    public static final Color color_yellow = new Color(255, 180, 0);
    public static final Color color_orange = new Color(255, 120, 30);
    public static final Color color_gray   = new Color(200, 200, 200);
    public static final int alpha_vibrant = 140;
    public static final int alpha_normal = 80;

    private boolean isValidColor(final Color color) {
        return (color != null && color.getAlpha() > 0);
    }

    public static Color getColor(final Color color, final int alpha) {
        return color == null ? null : new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public void renderTile(final Graphics2D graphics, final TileObject object, final Color color) {
        renderShape(graphics, object.getCanvasTilePoly(), color);
    }

    public void renderShape(final Graphics2D graphics, final Shape shape, final Color color) {
        if (!isValidColor(color)) return;

        try {
            // Area border.
            graphics.setColor(darkenColor(color));
            graphics.setStroke(new BasicStroke(clickbox_stroke_width));
            graphics.draw(shape);

            // Area fill.
            graphics.setColor(color);
            graphics.fill(shape);
        } catch (Exception ignored) {}
    }

    public void renderPie(final Graphics2D graphics, final TileObject object, final Color color, final float progress) {
        renderPie(graphics, object, color, progress, 0);
    }

    public void renderPie(final Graphics2D graphics, final TileObject object, final Color color, final float progress, final int offset) {
        if (!isValidColor(color)) return;

        try {
            final ProgressPieComponent progressPieComponent = new ProgressPieComponent();
            progressPieComponent.setPosition(object.getCanvasLocation(offset));
            progressPieComponent.setProgress(-progress);
            progressPieComponent.setBorderColor(darkenColor(color));
            progressPieComponent.setFill(color);
            progressPieComponent.render(graphics);
        } catch (Exception ignored) {}
    }

    public TileObject findTileObject(final Client client, final int x, final int y, final int id) {
        try {
            final Scene scene = client.getScene();
            final Tile[][][] tiles = scene.getTiles();
            final Tile tile = tiles[client.getPlane()][x][y];

            if (tile != null) {
                for (GameObject game_object : tile.getGameObjects()) {
                    if (game_object != null && game_object.getId() == id) {
                        return game_object;
                    }
                }

                final WallObject wall_object = tile.getWallObject();
                if (wall_object != null && wall_object.getId() == id) {
                    return wall_object;
                }

                final DecorativeObject decorative_object = tile.getDecorativeObject();
                if (decorative_object != null && decorative_object.getId() == id) {
                    return decorative_object;
                }

                final GroundObject ground_object = tile.getGroundObject();
                if (ground_object != null && ground_object.getId() == id) {
                    return ground_object;
                }
            }
        } catch (Exception ignored) {}

        return null;
    }

    public Color darkenColor(final Color color) {
        if (!isValidColor(color)) return null;
        final float factor = 0.8f;

        final int a = color.getAlpha();
        final int r = Math.round(color.getRed() * factor);
        final int g = Math.round(color.getGreen() * factor);
        final int b = Math.round(color.getBlue() * factor);

        return new Color(Math.min(r, 255), Math.min(g, 255), Math.min(b, 255), a);
    }
}