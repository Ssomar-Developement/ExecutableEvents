package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDismountEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerDismountEvent implements Listener {

    @EventHandler
    public void entityDismountEvent(EntityDismountEvent e) {
        if (e.getEntity() instanceof Player) {
            EventInfo eInfo = new EventInfo(e);
            eInfo.setPlayer(Optional.of((Player) e.getEntity()));
            eInfo.setTargetEntity(Optional.of(e.getDismounted()));
            EventsManager.getInstance().activeOption(Option.PLAYER_DISMOUNT, eInfo, new ArrayList<>());
        }
    }
}
