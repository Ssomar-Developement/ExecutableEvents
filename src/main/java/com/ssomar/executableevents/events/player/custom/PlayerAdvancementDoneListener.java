package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.Optional;

public class PlayerAdvancementDoneListener implements Listener {

    @EventHandler
    public void onPlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.getPlaceholders().put("%advancement%", String.valueOf(e.getAdvancement().displayName().toString()));
        eInfo.setOption(Option.PLAYER_ADVANCEMENT);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
