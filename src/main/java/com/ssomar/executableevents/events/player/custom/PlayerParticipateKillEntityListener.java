package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.sevents.events.player.kill.entity.participate.entity.EntityParticipateKillEntityEvent;
import com.ssomar.sevents.events.player.kill.entity.participate.player.PlayerParticipateKillEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerParticipateKillEntityListener implements Listener {
    @EventHandler
    public void onPlayerParticipateKillEntity(PlayerParticipateKillEntityEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetEntity(Optional.of(e.getTarget()));
        EventsManager.getInstance().activeOption(Option.PLAYER_PARTICIPATE_KILL_ENTITY, eInfo, new ArrayList<>());
    }
}
