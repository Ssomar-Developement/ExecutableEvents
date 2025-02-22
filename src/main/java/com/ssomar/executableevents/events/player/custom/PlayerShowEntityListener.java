package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerShowEntityEvent;

import java.util.Optional;

public class PlayerShowEntityListener implements Listener {

    @EventHandler
    public void onPlayerShowEntityEvent(PlayerShowEntityEvent e) {

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetEntity(Optional.of(e.getEntity()));
        eInfo.setOption(Option.PLAYER_SHOW_ENTITY);
        EventsManager.getInstance().activeOption(eInfo);
    }
}