package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerProjectileHitPlayer implements Listener {

    @EventHandler
    public void projectileHitPlayer(org.bukkit.event.entity.ProjectileHitEvent e) {
        if (!(e.getEntity().getShooter() instanceof Player)) return;

        if (e.getHitEntity() == null) return;
        if (!(e.getHitEntity() instanceof Player)) return;

        Projectile arrow = e.getEntity();

        EventInfo eInfo = new EventInfo(e);

        eInfo.setPlayer(Optional.of((Player) arrow.getShooter()));

        eInfo.setTargetPlayer(Optional.ofNullable(((Player) e.getHitEntity()).getPlayer()));

        eInfo.setProjectile(Optional.of(arrow));

        EventsManager.getInstance().activeOption(Option.PLAYER_PROJECTILE_HIT_PLAYER, eInfo, new ArrayList<>());
    }
}
