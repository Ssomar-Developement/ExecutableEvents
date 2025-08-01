package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerDisableSneakEvent implements Listener {

    @EventHandler
    public void playerDesactiveSneakEvent(com.ssomar.sevents.events.player.sneak.disable.PlayerDisableSneakEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setOption(Option.PLAYER_DISABLE_SNEAK);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
