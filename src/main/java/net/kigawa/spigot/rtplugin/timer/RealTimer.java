package net.kigawa.spigot.rtplugin.timer;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Calendar;

public abstract class RealTimer extends Timer {

    public RealTimer(JavaPlugin plugin) {
        super(plugin);
        Calendar calendar = Calendar.getInstance();
        setSec(calendar.get(Calendar.SECOND));
        setMin(calendar.get(Calendar.MINUTE));
        setHour(calendar.get(Calendar.HOUR_OF_DAY));
    }

    @Override
    public void timer() {
        Calendar calendar = Calendar.getInstance();
        checkSec(calendar);
    }

    public void checkSec(Calendar calendar) {
        int sec = calendar.get(Calendar.SECOND);
        if (sec != getSec()) {
            setSec(sec);
            secTimer();
            checkMin(calendar);
        }
    }

    public void checkMin(Calendar calendar) {
        int min = calendar.get(Calendar.MINUTE);
        if (min != getMin()) {
            setMin(min);
            minTimer();
            checkHour(calendar);
        }
    }

    public void checkHour(Calendar calendar) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour != getHour()) {
            setHour(hour);
            hourTimer();
        }
    }
}
