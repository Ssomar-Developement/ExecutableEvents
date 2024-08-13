package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityTargetPlayerListener implements Listener {

    @EventHandler
    public void onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent e) {

        if(e.getTarget() instanceof Player) {
            EventInfo eInfo = new EventInfo(e);
            eInfo.setEntity(Optional.of(e.getEntity()));
            eInfo.setTargetPlayer(Optional.of((Player)e.getTarget()));
            eInfo.setOption(Option.ENTITY_TARGET_PLAYER);
            EventsManager.getInstance().activeOption(eInfo);
        }
    }
}
