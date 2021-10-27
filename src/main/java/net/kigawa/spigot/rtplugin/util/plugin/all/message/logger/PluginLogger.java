package net.kigawa.spigot.rtplugin.util.plugin.all.message.logger;

import net.kigawa.spigot.rtplugin.util.all.Logger;
import net.kigawa.spigot.rtplugin.util.plugin.all.PluginBase;

public abstract class PluginLogger implements Logger {
    PluginBase plugin;

    public PluginLogger(PluginBase kigawaPlugin) {
        plugin = kigawaPlugin;
    }

    @Override
    public void logger(String message) {
        plugin.logger(message);
    }
}
