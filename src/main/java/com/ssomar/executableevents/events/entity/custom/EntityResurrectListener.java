package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityResurrectEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityResurrectListener implements Listener {

    @EventHandler
    public void onEntityResurrectEvent(EntityResurrectEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setOption(Option.ENTITY_RESURRECT);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
