package net.runelite.client.plugins.microbot.zalcano;

import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

public class ZalcanoPanel extends Overlay
{
    private final ZalcanoPlugin plugin;
    private Rectangle resetButtonArea;

    @Inject
    private ZalcanoPanel(ZalcanoPlugin plugin)
    {
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        int y = 20;
        graphics.setColor(Color.WHITE);

        graphics.drawString("Daño al escudo: " + plugin.getShieldDamage(), 10, y);
        y += 15;
        graphics.drawString("Daño a Zalcano: " + plugin.getHealthDamage(), 10, y);
        y += 15;

        // Botón Reset
        String resetText = "[ Reset Historial ]";
        graphics.setColor(Color.CYAN);
        graphics.drawString(resetText, 10, y);
        resetButtonArea = new Rectangle(10, y - 12, graphics.getFontMetrics().stringWidth(resetText), 15);
        y += 20;

        // Historial con gráfico
        List<Integer> shieldHist = plugin.getShieldHistory();
        List<Integer> healthHist = plugin.getHealthHistory();
        List<Boolean> lootHist = plugin.getLootHistory();
        List<Boolean> petHist = plugin.getSmolcanoHistory();

        graphics.setColor(Color.WHITE);

        if (!shieldHist.isEmpty())
        {
            graphics.drawString("--- Historial de kills ---", 10, y);
            y += 15;

            int maxBarHeight = 50;
            int maxDamage = Math.max(
                    shieldHist.stream().max(Integer::compare).orElse(0),
                    healthHist.stream().max(Integer::compare).orElse(0)
            );

            for (int i = 0; i < shieldHist.size(); i++)
            {
                int shield = shieldHist.get(i);
                int health = healthHist.get(i);

                String text = String.format(
                        "Kill %d | Loot: %s | Smolcano: %s",
                        i + 1,
                        lootHist.get(i) ? "✅" : "❌",
                        petHist.get(i) ? "🎉" : "💀"
                );
                graphics.drawString(text, 10, y);
                y += 15;

                int barWidth = 10;
                int shieldBarHeight = (int)((double)shield / maxDamage * maxBarHeight);
                int healthBarHeight = (int)((double)health / maxDamage * maxBarHeight);

                graphics.setColor(Color.RED);
                graphics.fillRect(10, y + maxBarHeight - shieldBarHeight, barWidth, shieldBarHeight);

                graphics.setColor(Color.GREEN);
                graphics.fillRect(25, y + maxBarHeight - healthBarHeight, barWidth, healthBarHeight);

                y += maxBarHeight + 10;
            }
        }

        return null;
    }

    public void handleMouseClick(MouseEvent e)
    {
        if (resetButtonArea != null && resetButtonArea.contains(e.getPoint()))
        {
            plugin.getShieldHistory().clear();
            plugin.getHealthHistory().clear();
            plugin.getLootHistory().clear();
            plugin.getSmolcanoHistory().clear();
            Microbot.log("🗑 Historial de Zalcano reseteado.");
        }
    }
}
