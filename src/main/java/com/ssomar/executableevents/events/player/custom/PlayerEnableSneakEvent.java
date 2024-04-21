package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerEnableSneakEvent implements Listener {

    @EventHandler
    public void playerEnableSneakEvent(com.ssomar.sevents.events.player.sneak.enable.PlayerEnableSneakEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        EventsManager.getInstance().activeOption(Option.PLAYER_ENABLE_SNEAK, eInfo, new ArrayList<>());
    }
}
