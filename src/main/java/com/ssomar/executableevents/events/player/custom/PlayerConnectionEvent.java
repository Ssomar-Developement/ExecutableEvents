package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.SCore;
import com.ssomar.score.data.CooldownsQuery;
import com.ssomar.score.data.Database;
import com.ssomar.score.features.custom.cooldowns.CooldownsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerConnectionEvent implements Listener {

    // Delayed of 5 ticks to be sure that the player cooldowns are loaded
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskLater(SCore.plugin, () -> {
            EventInfo eInfo = new EventInfo(e);
            eInfo.setPlayer(Optional.of(e.getPlayer()));
            eInfo.setOption(Option.PLAYER_CONNECTION);
            EventsManager.getInstance().activeOption(eInfo);
        }, 5);
    }
}
