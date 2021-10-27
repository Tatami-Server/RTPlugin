package net.kigawa.spigot.rtplugin;

import net.kigawa.spigot.rtplugin.util.plugin.all.PluginBase;
import org.bukkit.Bukkit;

public abstract class Timer {
    private final PluginBase plugin;
    private boolean timer = true;
    private int tick = 0;
    private int sec = 0;
    private int min = 0;
    private int hour = 0;

    public Timer(PluginBase plugin) {
        this.plugin = plugin;
    }

    public PluginBase getPlugin() {
        return plugin;
    }

    public abstract void tickTimer();

    public abstract void secTimer();

    public abstract void minTimer();

    public abstract void hourTimer();

    public void timer() {
        if (timer) {
            tickTimer();
            tick++;

            if (tick >= 20) {
                secTimer();
                tick = 0;
                sec++;

                if (sec >= 60) {
                    minTimer();
                    sec = 0;
                    min++;

                    if (min >= 60) {
                        hourTimer();
                        min = 0;
                        hour++;
                    }
                }
            }
            Bukkit.getScheduler().runTaskLater(plugin, this::timer, 1);
        }
    }
}
