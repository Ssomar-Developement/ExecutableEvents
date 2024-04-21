package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SsomarDev;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class EntityBeforeDeathEvent implements Listener {

    @EventHandler
    public void entityBeforeDeathEvent(com.ssomar.sevents.events.entity.beforedeath.EntityBeforeDeathEvent e) {
        //SsomarDev.testMsg("EntityBeforeDeathEvent entityBeforeDeathEvent", true);
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        EventsManager.getInstance().activeOption(Option.ENTITY_BEFORE_DEATH, eInfo, new ArrayList<>());
    }
}
