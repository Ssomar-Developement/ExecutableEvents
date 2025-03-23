package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;

import java.util.Optional;

public class EntityTeleportListener implements Listener {

    @EventHandler
    public void onEntityTeleportEvent(EntityTeleportEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setOption(Option.ENTITY_TELEPORT);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
