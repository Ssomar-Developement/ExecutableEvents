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

public class EntityTargetEntityListener implements Listener {

    @EventHandler
    public void onEntityTargetLivingEntityEvent(EntityTargetLivingEntityEvent e) {

        // The target is null when the mob loses its target (TargetGoal.stop -> setTarget(null)),
        // which happens for every mob: nothing to run in that case.
        if (e.getTarget() == null) return;

        if(e.getTarget() instanceof Player) {
            if (!e.getTarget().hasMetadata("NPC")) return;
        }
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setTargetEntity(Optional.of(e.getTarget()));
        eInfo.setOption(Option.ENTITY_TARGET_ENTITY);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
