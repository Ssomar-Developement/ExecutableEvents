package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerBedEnterEvent implements Listener {

    @EventHandler
    public void onPlayerBedEnterEvent(org.bukkit.event.player.PlayerBedEnterEvent e) {

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        if(e.getBedEnterResult().equals(org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult.OK)) {
            eInfo.setOption(Option.PLAYER_BED_ENTER);
            EventsManager.getInstance().activeOption(eInfo);
        }
    }
}
