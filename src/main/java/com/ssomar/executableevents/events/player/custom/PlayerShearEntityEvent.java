package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerShearEntityEvent implements Listener {

    @EventHandler
    public void onPlayerShearEntityEvent(org.bukkit.event.player.PlayerShearEntityEvent e) {

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetEntity(Optional.of(e.getEntity()));
        EventsManager.getInstance().activeOption(Option.PLAYER_SHEAR_ENTITY, eInfo, new ArrayList<>());
    }
}
