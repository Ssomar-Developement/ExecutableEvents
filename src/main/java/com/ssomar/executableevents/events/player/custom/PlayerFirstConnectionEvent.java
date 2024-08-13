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
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerFirstConnectionEvent implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        BukkitRunnable r = new BukkitRunnable() {
            @Override
            public void run() {
                //SsomarDev.testMsg("onPlayerJoinEvent >> "+e.getPlayer().hasPlayedBefore(), true);
                if(e.getPlayer().hasPlayedBefore()) return;
                EventInfo eInfo = new EventInfo(e);
                eInfo.setPlayer(Optional.of(e.getPlayer()));
                eInfo.setOption(Option.PLAYER_FIRST_CONNECTION);
                EventsManager.getInstance().activeOption(eInfo);
            }
        };
        SCore.schedulerHook.runEntityTask(r, null, e.getPlayer(), 5);
    }
}
