package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerConsumeEvent implements Listener {

    @EventHandler
    public void onPlayerItemConsumeEvent(PlayerItemConsumeEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setItem(Optional.of(e.getItem()));
        EventsManager.getInstance().activeOption(Option.PLAYER_CONSUME, eInfo, new ArrayList<>());
    }
}
