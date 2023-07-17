package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEntityEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerBucketEntityListener implements Listener {

    @EventHandler
    public void onPlayerBucketEntityEvent(PlayerBucketEntityEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetEntity(Optional.of(e.getEntity()));
        EventsManager.getInstance().activeOption(Option.PLAYER_BUCKET_ENTITY, eInfo, new ArrayList<>());
    }
}
