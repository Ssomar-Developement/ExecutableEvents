package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreakDoorEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityBreakDoorListener implements Listener {

    @EventHandler
    public void onEntityBreakDoorEvent(EntityBreakDoorEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setTargetBlock(Optional.of(e.getBlock()));
        eInfo.setOldMaterialTargetBlock(Optional.of(e.getBlock().getType()));
        if (!SCore.is1v12Less()) eInfo.setOldStatesTargetBlock(Optional.of(e.getBlock().getBlockData().getAsString(true)));
        eInfo.setOption(Option.ENTITY_BREAK_DOOR);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
