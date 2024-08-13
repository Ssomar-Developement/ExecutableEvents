package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerFishFishEvent implements Listener {

    @EventHandler
    public void playerFishFishEvent(com.ssomar.sevents.events.player.fish.fish.PlayerFishFishEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetEntity(Optional.of(e.getCaught()));
        eInfo.setOption(Option.PLAYER_FISH_FISH);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
