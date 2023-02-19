package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerTargetedByAnEntityEvent implements Listener {

    @EventHandler
    public void playerTargetedByAnEntityEvent(EntityTargetEvent e) {
        if(e.getTarget() instanceof Player) {
            EventInfo eInfo = new EventInfo(e);
            eInfo.setPlayer(Optional.of((Player)e.getTarget()));
            eInfo.setTargetEntity(Optional.of(e.getEntity()));
            EventsManager.getInstance().activeOption(Option.PLAYER_TARGETED_BY_AN_ENTITY, eInfo, new ArrayList<>());
        }
    }
}
