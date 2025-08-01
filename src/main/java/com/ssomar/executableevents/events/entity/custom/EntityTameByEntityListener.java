package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityTameByEntityListener implements Listener {

    @EventHandler
    public void onEntityTameEvent(EntityTameEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        if (!(e.getOwner() instanceof Player) && e.getOwner() instanceof LivingEntity) eInfo.setTargetEntity(Optional.of((LivingEntity) e.getOwner()));
        eInfo.setOption(Option.ENTITY_TAME_BY_ENTITY);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
