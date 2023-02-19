package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
import org.bukkit.event.entity.EntityBreedEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EnderdragonChangePhaseListener implements Listener {

    @EventHandler
    public void onEnderDragonChangePhaseEvent(EnderDragonChangePhaseEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        EventsManager.getInstance().activeOption(Option.ENDERDRAGON_CHANGE_PHASE, eInfo, new ArrayList<>());
    }
}
