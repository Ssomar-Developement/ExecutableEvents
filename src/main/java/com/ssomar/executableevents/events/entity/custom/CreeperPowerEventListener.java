package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreeperPowerEvent;

import java.util.Optional;

public class CreeperPowerEventListener implements Listener {

    @EventHandler
    public void creeperPowerEvent(CreeperPowerEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.getPlaceholders().put("%PowerCause%", String.valueOf(e.getCause()));
        eInfo.setOption(Option.CREEPER_POWER_CHANGE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
