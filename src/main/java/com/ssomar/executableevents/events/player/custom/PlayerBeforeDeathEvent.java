package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerBeforeDeathEvent implements Listener {

    @EventHandler
    public void playerBeforeDeathEvent(com.ssomar.sevents.events.player.beforedeath.PlayerBeforeDeathEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setOption(Option.PLAYER_BEFORE_DEATH);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
