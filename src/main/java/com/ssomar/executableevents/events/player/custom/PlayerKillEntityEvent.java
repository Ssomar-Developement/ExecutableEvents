package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerKillEntityEvent implements Listener {

    @EventHandler
    public void playerKillEntityEvent(com.ssomar.sevents.events.player.kill.entity.PlayerKillEntityEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetEntity(Optional.of(e.getEntity()));
        eInfo.setOption(Option.PLAYER_KILL_ENTITY);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
