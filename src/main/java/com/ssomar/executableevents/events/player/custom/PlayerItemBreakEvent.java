package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerItemBreakEvent implements Listener {

    @EventHandler
    public void onPlayerItemBreakEvent(org.bukkit.event.player.PlayerItemBreakEvent e) {

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setOption(Option.PLAYER_ITEM_BREAK);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
