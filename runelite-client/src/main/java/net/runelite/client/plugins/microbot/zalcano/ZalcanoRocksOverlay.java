package net.runelite.client.plugins.microbot.zalcano;

import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;

import javax.inject.Inject;
import java.awt.*;
import java.util.Set;

public class ZalcanoRocksOverlay extends Overlay
{
    private final ZalcanoPlugin plugin;
    private final Client client;

    @Inject
    private ZalcanoRocksOverlay(ZalcanoPlugin plugin, Client client)
    {
        this.plugin = plugin;
        this.client = client;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        Set<WorldPoint> fallingRocks = plugin.getFallingRocks();

        for (WorldPoint rock : fallingRocks)
        {
            LocalPoint lp = LocalPoint.fromWorld(client, rock);
            if (lp != null)
            {
                Polygon poly = Perspective.getCanvasTilePoly(client, lp);
                if (poly != null)
                {
                    OverlayUtil.renderPolygon(graphics, poly, new Color(255, 0, 0, 100));
                }
            }
        }
        return null;
    }
}
