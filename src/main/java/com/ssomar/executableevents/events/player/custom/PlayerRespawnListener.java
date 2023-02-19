package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerRespawnListener implements Listener {

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {

       // SsomarDev.testMsg("onPlayerRespawnEvent", true);

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        EventsManager.getInstance().activeOption(Option.PLAYER_RESPAWN, eInfo, new ArrayList<>());
    }
}
