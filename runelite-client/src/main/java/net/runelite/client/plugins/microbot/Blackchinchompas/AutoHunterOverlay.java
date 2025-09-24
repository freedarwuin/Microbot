package net.runelite.client.plugins.microbot.Blackchinchompas;

import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Blackchinchompas.scripts.AutoChinScript;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;

public class AutoHunterOverlay extends OverlayPanel {

    @Inject
    AutoHunterOverlay() {
        setPosition(OverlayPosition.TOP_LEFT);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        panelComponent.setPreferredSize(new Dimension(200, 300));

        panelComponent.getChildren().add(TitleComponent.builder()
                .text("Micro Auto Hunter " + AutoChinScript.version)
                .color(Color.GREEN)
                .build());

        panelComponent.getChildren().add(LineComponent.builder()
                .left(Microbot.status)
                .build());

        return super.render(graphics);
    }
}
