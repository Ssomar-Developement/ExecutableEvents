package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.entity.EntityPortalExitEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityPortalExitListener implements Listener {

    @EventHandler
    public void onEntityPortalExitEvent(EntityPortalExitEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        EventsManager.getInstance().activeOption(Option.ENTITY_PORTAL_EXIT, eInfo, new ArrayList<>());
    }
}
