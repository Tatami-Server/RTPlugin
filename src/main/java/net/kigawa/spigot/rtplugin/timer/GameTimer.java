package net.kigawa.spigot.rtplugin.timer;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class GameTimer extends Timer {
    private int tick = 0;

    public GameTimer(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void timer() {
        tick++;
        if (tick >= 20) {
            secTimer();
            tick = 0;
            addSec();

            if (getSec() >= 60) {
                minTimer();
                setSec(0);
                addMin();

                if (getMin() >= 60) {
                    hourTimer();
                    setMin(0);
                    addHour();
                }
            }
        }
    }
}
