package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class EntityProjectileHitEntity implements Listener {

    @EventHandler
    public void projectileHitEntity(com.ssomar.sevents.events.projectile.hitentity.ProjectileHitEntityEvent e) {

        Projectile arrow = (Projectile) e.getEntity();

        if (arrow.getShooter() instanceof Player) return;

        EventInfo eInfo = new EventInfo(e);

        eInfo.setEntity(Optional.of((Entity) arrow.getShooter()));
        eInfo.setTargetEntity(Optional.ofNullable(e.getTarget()));
        eInfo.setProjectile(Optional.of(arrow));

        EventsManager.getInstance().activeOption(Option.ENTITY_PROJECTILE_HIT_ENTITY, eInfo, new ArrayList<>());
    }
}
