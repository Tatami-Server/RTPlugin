package net.kigawa.spigot.rtplugin.timer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public abstract class Timer {
    private final JavaPlugin plugin;
    private int sec = 0;
    private int min = 0;
    private int hour = 0;
    private BukkitTask task;

    public Timer(JavaPlugin plugin) {
        this.plugin = plugin;
        startTimer();
    }

    public abstract void tickTimer();

    public abstract void secTimer();

    public abstract void minTimer();

    public abstract void hourTimer();

    public abstract void timer();

    public void startTimer() {
        endTimer();
        task = Bukkit.getScheduler().runTaskTimer(plugin, this::tick, 0, 1);
    }

    public void endTimer() {
        if (task == null) return;
        task.cancel();
        task = null;
    }

    public void tick() {
        timer();
        tickTimer();
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public void addSec() {
        sec++;
    }

    public void addMin() {
        min++;
    }

    public void addHour() {
        hour++;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getSec() {
        return sec;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
}
