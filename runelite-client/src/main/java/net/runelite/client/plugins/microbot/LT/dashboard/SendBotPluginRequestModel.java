package net.runelite.client.plugins.microbot.LT.dashboard;

import java.util.List;

public class SendBotPluginRequestModel {
    String group;
    List<PluginRequestModel> plugins;

    public SendBotPluginRequestModel(String group, List<PluginRequestModel> plugins) {
        this.group = group;
        this.plugins = plugins;
    }
}
