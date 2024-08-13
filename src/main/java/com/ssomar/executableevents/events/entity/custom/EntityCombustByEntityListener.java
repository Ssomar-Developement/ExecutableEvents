package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityCombustByEntityListener implements Listener {

    @EventHandler
    public void onEntityCombustByEntityEvent(EntityCombustByEntityEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setTargetEntity(Optional.of(e.getCombuster()));
        eInfo.setOption(Option.ENTITY_COMBUST_BY_ENTITY);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
