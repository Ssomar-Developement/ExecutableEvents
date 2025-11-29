package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerReceiveHitByEntityEvent implements Listener {

    @EventHandler
    public void playerReceiveHitByEntityEvent(com.ssomar.sevents.events.player.receivehit.byentity.PlayerReceiveHitByEntityEvent e) {


        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetEntity(Optional.of(e.getDamager()));
        eInfo.setDamageCause(Optional.of(e.getDamageCause()));
        if (e.getDamager() instanceof Projectile) {
            eInfo.setProjectile(Optional.of((Projectile) e.getDamager()));
        }
        eInfo.getPlaceholders().put("%last_damage_taken_nonfinal%", String.valueOf(e.getEntityDamageEvent().getDamage()));
        eInfo.getPlaceholders().put("%last_damage_taken_nonfinal_int%", String.valueOf((int) e.getEntityDamageEvent().getDamage()));
        eInfo.getPlaceholders().put("%last_damage_taken_final%", String.valueOf(e.getEntityDamageEvent().getFinalDamage()));
        eInfo.getPlaceholders().put("%last_damage_taken_final_int%", String.valueOf((int) e.getEntityDamageEvent().getFinalDamage()));
        eInfo.setOption(Option.PLAYER_RECEIVE_HIT_BY_ENTITY);
        EventsManager.getInstance().activeOption(eInfo);
    }

    @EventHandler
    public void projectileHitPlayerEvent(com.ssomar.sevents.events.projectile.hitplayer.ProjectileHitPlayerEvent e) {

        if (e.getEntity() instanceof Projectile) {
            Projectile projectile = (Projectile) e.getEntity();
            if (projectile.getShooter() instanceof Entity && !(projectile.getShooter() instanceof Player)) {
                EventInfo eInfo = new EventInfo(e);
                eInfo.setPlayer(Optional.of(e.getTarget()));
                eInfo.setTargetEntity(Optional.of((Entity) projectile.getShooter()));
                eInfo.setDamageCause(Optional.of(EntityDamageEvent.DamageCause.PROJECTILE));
                eInfo.getPlaceholders().put("%last_damage_taken_nonfinal%", String.valueOf(e.getEntity().getLastDamageCause().getDamage()));
                eInfo.getPlaceholders().put("%last_damage_taken_nonfinal_int%", String.valueOf((int) e.getEntity().getLastDamageCause().getDamage()));
                eInfo.getPlaceholders().put("%last_damage_taken_final%", String.valueOf(e.getEntity().getLastDamageCause().getFinalDamage()));
                eInfo.getPlaceholders().put("%last_damage_taken_final_int%", String.valueOf((int) e.getEntity().getLastDamageCause().getFinalDamage()));
                eInfo.setOption(Option.PLAYER_RECEIVE_HIT_BY_ENTITY);
                EventsManager.getInstance().activeOption(eInfo);
            }
        }
    }
}
