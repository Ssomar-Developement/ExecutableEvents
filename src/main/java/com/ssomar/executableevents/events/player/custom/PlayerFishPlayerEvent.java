package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerFishPlayerEvent implements Listener {

    @EventHandler
    public void playerFishPlayerEvent(com.ssomar.sevents.events.player.fish.player.PlayerFishPlayerEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetPlayer(Optional.of(e.getTarget()));
        eInfo.setOption(Option.PLAYER_FISH_PLAYER);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
