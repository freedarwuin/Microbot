package net.runelite.client.plugins.microbot.chatmessenger;

import net.runelite.client.ui.PluginPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class ChatMessengerPanel extends PluginPanel
{
    private final ChatMessengerPlugin plugin;

    public ChatMessengerPanel(ChatMessengerPlugin plugin)
    {
        this.plugin = plugin;
        setLayout(new BorderLayout());

        JButton toggleButton = new JButton("Activar/Desactivar Chat");
        toggleButton.addActionListener(e -> plugin.toggleMessaging());

        add(toggleButton, BorderLayout.NORTH);
    }
}
