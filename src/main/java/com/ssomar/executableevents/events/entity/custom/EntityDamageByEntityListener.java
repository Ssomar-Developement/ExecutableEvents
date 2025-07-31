package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.commands.runnable.mixed_player_entity.commands.DamageBoost;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.sevents.events.player.receivehit.byentity.PlayerReceiveHitByEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByBlockEvenEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {

        if(e.getDamager() instanceof Player) {
            if (!e.getDamager().hasMetadata("NPC")) return;
        }
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setTargetEntity(Optional.of(e.getDamager()));
        eInfo.setDamageCause(Optional.of(e.getCause()));
        eInfo.setOption(Option.ENTITY_DAMAGE_BY_ENTITY);
        eInfo.getPlaceholders().put("entity_last_damage_taken_final%", String.valueOf(e.getFinalDamage()));
        eInfo.getPlaceholders().put("entity_last_damage_taken_final_int%", String.valueOf( (int) ( e.getFinalDamage() ) ));
        EventsManager.getInstance().activeOption(eInfo);
    }
}
