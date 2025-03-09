package com.ssomar.executableevents.events.weather.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.Optional;

public class WeatherChangeListener implements Listener {

    @EventHandler
    public void WeatherChangeEvent(WeatherChangeEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getWorld()));
        eInfo.setBlock(Optional.of(e.getWorld().getSpawnLocation().getBlock()));
        eInfo.getPlaceholders().put("%cause%", String.valueOf(Optional.of(e.getCause().toString())));
        eInfo.setOption(Option.WEATHER_CHANGE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
