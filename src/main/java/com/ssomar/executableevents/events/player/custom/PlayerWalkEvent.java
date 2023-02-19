package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerWalkEvent implements Listener {

    @EventHandler
    public void onPlayerWalkEvent(PlayerMoveEvent e) {

        Location from = e.getFrom();
        Location to = e.getTo();

        if (from.getX() == to.getX() && from.getZ() == to.getZ()) return;

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        EventsManager.getInstance().activeOption(Option.PLAYER_WALK, eInfo, new ArrayList<>());
    }
}
