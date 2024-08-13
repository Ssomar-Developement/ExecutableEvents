package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSpawnChangeEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerSpawnChangeListener implements Listener {

    @EventHandler
    public void onPlayerSpawnChangeEvent(PlayerSpawnChangeEvent e) {

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setOption(Option.PLAYER_SPAWN_CHANGE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
