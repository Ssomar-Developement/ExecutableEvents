package com.ssomar.executableevents.events.weather.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;

import java.util.Optional;

public class LightningStrikeListener implements Listener {

    @EventHandler
    public void LightningStrikeEvent(LightningStrikeEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getWorld()));
        eInfo.setBlock(Optional.of(e.getWorld().getSpawnLocation().getBlock()));
        eInfo.getPlaceholders().put("%cause%", String.valueOf(Optional.of(e.getCause().toString())));
        eInfo.setOption(Option.LIGHTNING_STRIKE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
