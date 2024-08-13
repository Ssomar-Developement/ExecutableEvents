package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.events.PlayerCustomLaunchEntityEvent;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerCustomLaunchListener implements Listener {

    private static final Boolean DEBUG = false;


    @EventHandler
    public void onProjectileLaunchEvent(PlayerCustomLaunchEntityEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        if (e.getLaunchedEntity() instanceof Projectile)
            eInfo.setProjectile(Optional.of((Projectile) e.getLaunchedEntity()));
        eInfo.setTargetEntity(Optional.of(e.getLaunchedEntity()));
        eInfo.setOption(Option.PLAYER_CUSTOM_LAUNCH);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
