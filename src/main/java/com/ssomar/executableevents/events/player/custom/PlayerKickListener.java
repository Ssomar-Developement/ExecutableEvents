package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerKickListener implements Listener {

    @EventHandler
    public void onPlayerKickEvent(PlayerKickEvent e) {

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of((Player) e.getPlayer()));
        eInfo.setOption(Option.PLAYER_KICK);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
