package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.commands.runnable.mixed_player_entity.commands.DamageBoost;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityDamageByPlayerListener implements Listener {

    @EventHandler
    public void onEntityDamageByBlockEvenEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {

        if(e.getDamager() instanceof Player) {
            EventInfo eInfo = new EventInfo(e);
            eInfo.setEntity(Optional.of(e.getEntity()));
            eInfo.setTargetPlayer(Optional.of((Player)e.getDamager()));
            eInfo.setDamageCause(Optional.of(e.getCause()));
            eInfo.setOption(Option.ENTITY_DAMAGE_BY_PLAYER);
            eInfo.getPlaceholders().put("%entity_last_damage_taken_final%", String.valueOf(e.getFinalDamage()));
            eInfo.getPlaceholders().put("%entity_last_damage_taken_final_int%", String.valueOf( (int) ( e.getFinalDamage() ) ));
            eInfo.getPlaceholders().put("%entity_last_damage_taken_final_withBooster%", String.valueOf(DamageBoost.getInstance().getNewDamage(e.getDamager().getUniqueId(), e.getDamage())));
            eInfo.getPlaceholders().put("%entity_last_damage_taken_final_withBooster_int%", String.valueOf((int) DamageBoost.getInstance().getNewDamage(e.getDamager().getUniqueId(), e.getDamage())));
            EventsManager.getInstance().activeOption(eInfo);

        }
    }
}
