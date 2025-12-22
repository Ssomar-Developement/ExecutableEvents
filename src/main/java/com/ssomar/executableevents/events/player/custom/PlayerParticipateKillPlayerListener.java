package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.sevents.events.player.kill.player.participate.player.PlayerParticipateKillPlayerEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Optional;

public class PlayerParticipateKillPlayerListener implements Listener {
    @EventHandler
    public void onPlayerParticipateKillPlayer(PlayerParticipateKillPlayerEvent e) {
        // Skip if the participant is the killer (only process for assistants)
        Player killer = e.getTarget().getKiller();
        if (killer != null && killer.getUniqueId().equals(e.getPlayer().getUniqueId())) {
            return;
        }

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetPlayer(Optional.of(e.getTarget()));
        eInfo.setOption(Option.PLAYER_PARTICIPATE_KILL_PLAYER);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
