package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityCombustByBlockListener implements Listener {

    @EventHandler
    public void onEntityCombustByBlockEvent(EntityCombustByBlockEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setTargetBlock(Optional.of(e.getCombuster()));
        eInfo.setOldMaterialTargetBlock(Optional.of(e.getCombuster().getType()));
        if (!SCore.is1v12Less())
            eInfo.setOldStatesTargetBlock(Optional.of(e.getCombuster().getBlockData().getAsString(true)));
        EventsManager.getInstance().activeOption(Option.ENTITY_COMBUST_BY_BLOCK, eInfo, new ArrayList<>());
    }
}
