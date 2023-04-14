package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.SsomarDev;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerFirstConnectionEvent implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskLater(SCore.plugin, () -> {
            //SsomarDev.testMsg("onPlayerJoinEvent >> "+e.getPlayer().hasPlayedBefore(), true);
            if(e.getPlayer().hasPlayedBefore()) return;
            EventInfo eInfo = new EventInfo(e);
            eInfo.setPlayer(Optional.of(e.getPlayer()));
            EventsManager.getInstance().activeOption(Option.PLAYER_FIRST_CONNECTION, eInfo, new ArrayList<>());
        }, 5);
    }
}
