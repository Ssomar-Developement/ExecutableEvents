package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import java.util.Optional;

public class PlayerExpChangeListener implements Listener {

    @EventHandler
    public void onPlayerExpChangeEvent(PlayerExpChangeEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.getPlaceholders().put("%experience%", String.valueOf(e.getAmount()));
        eInfo.setOption(Option.PLAYER_EXPERIENCE_CHANGE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
