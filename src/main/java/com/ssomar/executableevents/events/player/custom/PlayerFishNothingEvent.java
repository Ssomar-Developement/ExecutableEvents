package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerFishNothingEvent implements Listener {

    @EventHandler
    public void playerFishNothingEvent(com.ssomar.sevents.events.player.fish.nothing.PlayerFishNothingEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetEntity(Optional.of(e.getBlob()));
        eInfo.setOption(Option.PLAYER_FISH_NOTHING);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
