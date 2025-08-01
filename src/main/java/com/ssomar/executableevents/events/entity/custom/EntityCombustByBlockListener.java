package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.block.Block;
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
        Block block = e.getCombuster();
        if (block == null) return;
        eInfo.setTargetBlock(Optional.of(block));
        eInfo.setOldMaterialTargetBlock(Optional.of(block.getType()));
        if (!SCore.is1v12Less()) eInfo.setOldStatesTargetBlock(Optional.of(block.getBlockData().getAsString(true)));
        eInfo.setOption(Option.ENTITY_COMBUST_BY_BLOCK);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
