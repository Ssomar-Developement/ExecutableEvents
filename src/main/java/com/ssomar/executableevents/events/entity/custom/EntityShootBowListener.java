package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.Optional;

public class EntityShootBowListener implements Listener {

    @EventHandler
    public void entityShootBowEvent(EntityShootBowEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setBowForce(Optional.of(e.getForce()));
        eInfo.setOption(Option.ENTITY_SHOOT_BOW);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
