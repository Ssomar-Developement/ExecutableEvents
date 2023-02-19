package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerReceiveHitGlobalEvent implements Listener {

    @EventHandler
    public void playerReceiveHitGlobalEvent(com.ssomar.sevents.events.player.receivehit.global.PlayerReceiveHitGlobalEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setDamageCause(Optional.of(e.getDamageCause()));
        EventsManager.getInstance().activeOption(Option.PLAYER_RECEIVE_HIT_GLOBAL, eInfo, new ArrayList<>());
    }
}
