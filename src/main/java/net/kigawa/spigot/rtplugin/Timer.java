package net.kigawa.spigot.rtplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Timer {
    private final JavaPlugin plugin;
    private boolean timer = true;
    private int tick = 0;
    private int sec = 0;
    private int min = 0;
    private int hour = 0;

    public Timer(JavaPlugin plugin) {
        this.plugin = plugin;
        timer();
    }

    public JavaPlugin getPlugin() {
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
