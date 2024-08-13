package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.score.utils.emums.DetailedClick;
import com.ssomar.sevents.events.player.click.onentity.left.PlayerLeftClickOnEntityEvent;
import com.ssomar.sevents.events.player.click.onentity.right.PlayerRightClickOnEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerClickOnEntityEvent implements Listener {

    @EventHandler
    public void playerRightClickOnEntityEvent(PlayerRightClickOnEntityEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetEntity(Optional.of(e.getEntity()));
        eInfo.setDetailedClick(Optional.of(DetailedClick.RIGHT));
        eInfo.setOption(Option.PLAYER_CLICK_ON_ENTITY);
        EventsManager.getInstance().activeOption(eInfo);
    }

    @EventHandler
    public void playerLeftClickOnEntityEvent(PlayerLeftClickOnEntityEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetEntity(Optional.of(e.getEntity()));
        eInfo.setDetailedClick(Optional.of(DetailedClick.LEFT));
        eInfo.setOption(Option.PLAYER_CLICK_ON_ENTITY);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
