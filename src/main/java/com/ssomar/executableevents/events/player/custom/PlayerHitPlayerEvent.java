package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerHitPlayerEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {

        //ignore fake events / required to ignore retard event from MythicLib io/lumine/mythic/lib/listener/SkillTriggers.java
        if(e.getDamage() == 0) return;

        if (e.getDamager() instanceof Player) {

            Player damager = (Player) e.getDamager();

            if (damager.hasMetadata("damageFromCustomCommand"))
                return;

            if (damager.hasMetadata("cancelDamageEvent") || e.getDamage() == 0)
                return;

            if (e.getEntity() instanceof Player) {

                /* NPC is not a player O_o */
                if (e.getEntity().hasMetadata("NPC")){
                   return;
                }
                else {
                    EventInfo eInfo = new EventInfo(e);
                    if(SCore.isPaperOrFork()){
                        eInfo.getPlaceholders().put("%critical%", e.isCritical() ? "true" : "false");
                    }
                    eInfo.setPlayer(Optional.of((Player)e.getDamager()));
                    eInfo.setTargetPlayer(Optional.of((Player)e.getEntity()));

                    eInfo.setOption(Option.PLAYER_HIT_PLAYER);
                    EventsManager.getInstance().activeOption(eInfo);
                }
            }
        }

    }
}
