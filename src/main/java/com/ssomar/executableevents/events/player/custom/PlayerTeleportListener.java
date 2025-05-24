package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerTeleportListener implements Listener {

    @EventHandler
    public void onEntityTeleportEvent(PlayerTeleportEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setOption(Option.PLAYER_TELEPORT);
        eInfo.getPlaceholders().put("%teleport_cause%", String.valueOf(e.getCause()));
        EventsManager.getInstance().activeOption(eInfo);
    }
}
