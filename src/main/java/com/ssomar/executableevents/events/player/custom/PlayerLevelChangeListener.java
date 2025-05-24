package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import java.util.Optional;

public class PlayerLevelChangeListener implements Listener {

    @EventHandler
    public void onPlayerLevelChange(PlayerLevelChangeEvent e) {

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.getPlaceholders().put("%new_level%", String.valueOf(e.getNewLevel()));
        eInfo.getPlaceholders().put("%old_level%", String.valueOf(e.getOldLevel()));
        eInfo.setOption(Option.PLAYER_LEVEL_CHANGE);
        EventsManager.getInstance().activeOption(eInfo);
    }

}
