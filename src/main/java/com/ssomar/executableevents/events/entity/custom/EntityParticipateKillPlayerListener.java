package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.sevents.events.player.kill.entity.participate.entity.EntityParticipateKillEntityEvent;
import com.ssomar.sevents.events.player.kill.player.participate.entity.EntityParticipateKillPlayerEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class EntityParticipateKillPlayerListener implements Listener {
    @EventHandler
    public void onEntityParticipateKillPlayer(EntityParticipateKillPlayerEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setTargetPlayer(Optional.of(e.getTarget()));
        eInfo.setOption(Option.ENTITY_PARTICIPATE_KILL_PLAYER);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
