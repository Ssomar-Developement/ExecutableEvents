package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.sevents.events.player.kill.entity.participate.player.PlayerParticipateKillEntityEvent;
import com.ssomar.sevents.events.player.kill.player.participate.player.PlayerParticipateKillPlayerEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerParticipateKillPlayerListener implements Listener {
    @EventHandler
    public void onPlayerParticipateKillPlayer(PlayerParticipateKillPlayerEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetPlayer(Optional.of(e.getTarget()));
        EventsManager.getInstance().activeOption(Option.PLAYER_PARTICIPATE_KILL_PLAYER, eInfo, new ArrayList<>());
    }
}
