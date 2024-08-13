package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.SsomarDev;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.Optional;

public class ProjectileLaunchListener implements Listener {

    private static final Boolean DEBUG = false;

    @EventHandler
    public void onProjectileLaunch(EntityShootBowEvent e) {
        SsomarDev.testMsg("LAUNCH ARROW", DEBUG);
        if (e.getEntity() instanceof Player) {
            /* add bow force to the entity */
            e.getProjectile().setMetadata("bowForce", new FixedMetadataValue(ExecutableEvents.plugin, e.getForce()));
        }
    }


    @EventHandler
    public void onProjectileLaunchEvent(ProjectileLaunchEvent e) {

        SsomarDev.testMsg("LAUNCH PROJECTILE", DEBUG);

        if (e.getEntity().getShooter() instanceof Player) {

            Player p = (Player) e.getEntity().getShooter();
            Projectile projectile = e.getEntity();

            EventInfo eInfo = new EventInfo(e);
            eInfo.setPlayer(Optional.of(p));
            eInfo.setProjectile(Optional.of(projectile));
            if (projectile.hasMetadata("bowForce")) {
                eInfo.setBowForce(Optional.of(projectile.getMetadata("bowForce").get(0).asFloat()));
            }
            eInfo.setVelocity(Optional.of(projectile.getVelocity()));
            eInfo.setTargetEntity(Optional.of(projectile));
            eInfo.setOption(Option.PLAYER_LAUNCH_PROJECTILE);
            EventsManager.getInstance().activeOption(eInfo);

        }
    }
}
