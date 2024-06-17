package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityMountEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerMountEvent implements Listener {

    @EventHandler
    public void entityMountEvent(EntityMountEvent e) {
        if (e.getEntity() instanceof Player) {
            EventInfo eInfo = new EventInfo(e);
            eInfo.setPlayer(Optional.of((Player) e.getEntity()));
            eInfo.setTargetEntity(Optional.of(e.getMount()));
            EventsManager.getInstance().activeOption(Option.PLAYER_MOUNT, eInfo, new ArrayList<>());
        }
    }
}
