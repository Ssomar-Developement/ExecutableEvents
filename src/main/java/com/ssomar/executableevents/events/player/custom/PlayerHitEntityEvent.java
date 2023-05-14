package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerHitEntityEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {

        //ignore fake events / required to ignore retard event from MythicLib io/lumine/mythic/lib/listener/SkillTriggers.java
        if(e.getDamage() == 0) return;

        if (e.getDamager() instanceof Player) {

            Player damager = (Player) e.getDamager();
            if (damager.hasMetadata("cancelDamageEvent"))
                return;

            if (!(e.getEntity() instanceof Player)) {
                EventInfo eInfo = new EventInfo(e);
                eInfo.setPlayer(Optional.of((Player) e.getDamager()));
                eInfo.setTargetEntity(Optional.of(e.getEntity()));
                EventsManager.getInstance().activeOption(Option.PLAYER_HIT_ENTITY, eInfo, new ArrayList<>());
            } else {
                /* NPC is not a player O_o */
                if (e.getEntity().hasMetadata("NPC")) {
                    EventInfo eInfo = new EventInfo(e);
                    eInfo.setPlayer(Optional.of((Player) e.getDamager()));
                    eInfo.setTargetEntity(Optional.of(e.getEntity()));
                    EventsManager.getInstance().activeOption(Option.PLAYER_HIT_ENTITY, eInfo, new ArrayList<>());
                }
            }
        }

    }
}
