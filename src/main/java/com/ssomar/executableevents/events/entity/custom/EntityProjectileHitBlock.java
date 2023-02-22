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

public class EntityProjectileHitBlock implements Listener {

    @EventHandler
    public void projectileHitBlock(org.bukkit.event.entity.ProjectileHitEvent e) {
        if ((e.getEntity().getShooter() instanceof Player)) return;
        if (!(e.getEntity() instanceof Entity)) return;

        if (e.getHitBlock() == null) return;

        Projectile arrow = e.getEntity();

        EventInfo eInfo = new EventInfo(e);

        eInfo.setEntity(Optional.of((Entity) arrow.getShooter()));

        eInfo.setTargetBlock(Optional.ofNullable(e.getHitBlock()));
        eInfo.setBlockface(Optional.ofNullable(e.getHitBlockFace().toString()));
        eInfo.setOldMaterialBlock(Optional.of(e.getHitBlock().getBlockData().getMaterial()));

        eInfo.setProjectile(Optional.of(arrow));

        EventsManager.getInstance().activeOption(Option.ENTITY_PROJECTILE_HIT_BLOCK, eInfo, new ArrayList<>());
    }
}
