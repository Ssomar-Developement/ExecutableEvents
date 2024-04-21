package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerDisableSprintEvent implements Listener {

    @EventHandler
    public void playerDisableSprintEvent(com.ssomar.sevents.events.player.sprint.disable.PlayerDisableSprintEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        EventsManager.getInstance().activeOption(Option.PLAYER_DISABLE_SPRINT, eInfo, new ArrayList<>());
    }
}
