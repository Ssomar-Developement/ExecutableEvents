package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerReceiveHitByPlayerEvent implements Listener {

    @EventHandler
    public void playerReceiveHitByPlayerEvent(com.ssomar.sevents.events.player.receivehit.byplayer.PlayerReceiveHitByPlayerEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetPlayer(Optional.of(e.getDamager()));
        eInfo.setDamageCause(Optional.of(e.getDamageCause()));
        EventsManager.getInstance().activeOption(Option.PLAYER_RECEIVE_HIT_BY_PLAYER, eInfo, new ArrayList<>());
    }

    @EventHandler
    public void projectileHitPlayerEvent(com.ssomar.sevents.events.projectile.hitplayer.ProjectileHitPlayerEvent e) {

        if (e.getEntity() instanceof Projectile) {
            Projectile projectile = (Projectile) e.getEntity();
            if (projectile.getShooter() instanceof Entity && (projectile.getShooter() instanceof Player)) {
                EventInfo eInfo = new EventInfo(e);
                eInfo.setPlayer(Optional.of(e.getTarget()));
                eInfo.setTargetPlayer(Optional.of((Player) projectile.getShooter()));
                eInfo.setDamageCause(Optional.of(EntityDamageEvent.DamageCause.PROJECTILE));
                EventsManager.getInstance().activeOption(Option.PLAYER_RECEIVE_HIT_BY_PLAYER, eInfo, new ArrayList<>());
            }
        }
    }
}
