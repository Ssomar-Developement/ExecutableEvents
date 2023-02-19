package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerFirstConnectionEvent implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {

        if(e.getPlayer().hasPlayedBefore()) return;
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        EventsManager.getInstance().activeOption(Option.PLAYER_CONNECTION, eInfo, new ArrayList<>());
    }
}
