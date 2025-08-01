package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerPortalListener implements Listener {

    @EventHandler
    public void onPlayerPortalEvent(org.bukkit.event.player.PlayerPortalEvent e) {
        Player p = e.getPlayer();

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(p));
        eInfo.setOption(Option.PLAYER_PORTAL);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
