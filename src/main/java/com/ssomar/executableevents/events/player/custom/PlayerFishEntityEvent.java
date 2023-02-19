package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerFishEntityEvent implements Listener {

    @EventHandler
    public void playerFishEntityEvent(com.ssomar.sevents.events.player.fish.entity.PlayerFishEntityEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetEntity(Optional.of(e.getEntity()));
        EventsManager.getInstance().activeOption(Option.PLAYER_FISH_ENTITY, eInfo, new ArrayList<>());
    }
}
