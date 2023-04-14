package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.score.utils.emums.DetailedClick;
import com.ssomar.sevents.events.player.click.onplayer.left.PlayerLeftClickOnPlayerEvent;
import com.ssomar.sevents.events.player.click.onplayer.right.PlayerRightClickOnPlayerEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerClickOnPlayerEvent implements Listener {

    @EventHandler
    public void playerRightClickOnPlayerEvent(PlayerRightClickOnPlayerEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetPlayer(Optional.of(e.getTarget()));
        eInfo.setDetailedClick(Optional.of(DetailedClick.RIGHT));
        EventsManager.getInstance().activeOption(Option.PLAYER_CLICK_ON_PLAYER, eInfo, new ArrayList<>());
    }

    @EventHandler
    public void playerLeftClickOnPlayerEvent(PlayerLeftClickOnPlayerEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetPlayer(Optional.of(e.getTarget()));
        eInfo.setDetailedClick(Optional.of(DetailedClick.LEFT));
        EventsManager.getInstance().activeOption(Option.PLAYER_CLICK_ON_PLAYER, eInfo, new ArrayList<>());
    }
}
