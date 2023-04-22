package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.sevents.events.player.kill.entity.participate.entity.EntityParticipateKillEntityEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class EntityParticipateKillEntityListener  implements Listener {
    @EventHandler
    public void onEntityParticipateKillEntity(EntityParticipateKillEntityEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setTargetEntity(Optional.of(e.getTarget()));
        EventsManager.getInstance().activeOption(Option.ENTITY_PARTICIPATE_KILL_ENTITY, eInfo, new ArrayList<>());
    }
}
