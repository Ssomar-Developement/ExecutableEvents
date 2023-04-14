package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerProjectileHitBlock implements Listener {

    @EventHandler
    public void projectileHitBlock(com.ssomar.sevents.events.projectile.hitblock.ProjectileHitBlockEvent e) {

        Projectile arrow = (Projectile) e.getEntity();

        if (!(arrow.getShooter() instanceof Player)) return;

        EventInfo eInfo = new EventInfo(e);

        eInfo.setPlayer(Optional.of((Player) arrow.getShooter()));


        eInfo.setBlockface(Optional.ofNullable(e.getBlockFace().toString()));
        eInfo.setTargetBlock(Optional.of(e.getBlock()));
        eInfo.setOldMaterialTargetBlock(Optional.of(e.getBlock().getType()));
        if (!SCore.is1v12Less()) eInfo.setOldStatesTargetBlock(Optional.of(e.getBlock().getBlockData().getAsString(true)));


        eInfo.setProjectile(Optional.of(arrow));

        EventsManager.getInstance().activeOption(Option.PLAYER_PROJECTILE_HIT_BLOCK, eInfo, new ArrayList<>());
    }
}
