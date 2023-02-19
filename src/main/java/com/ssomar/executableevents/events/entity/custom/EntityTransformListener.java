package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.entity.EntityTransformEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityTransformListener implements Listener {

    @EventHandler
    public void onEntityTransformEvent(EntityTransformEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setTargetEntity(Optional.of(e.getTransformedEntity()));
        EventsManager.getInstance().activeOption(Option.ENTITY_TRANSFORM, eInfo, new ArrayList<>());
    }
}
