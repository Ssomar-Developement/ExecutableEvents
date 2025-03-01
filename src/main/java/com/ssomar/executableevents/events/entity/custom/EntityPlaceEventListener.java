package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPlaceEvent;

import java.util.Optional;

public class EntityPlaceEventListener implements Listener {

    @EventHandler
    public void entityPlaceEvent(EntityPlaceEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setPlayer(Optional.ofNullable(e.getPlayer()));
        eInfo.setBlock(Optional.of(e.getBlock()));
        eInfo.setOption(Option.ENTITY_PLACE_EVENT);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
