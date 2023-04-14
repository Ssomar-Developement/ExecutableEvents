package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityDamageByBlockListener implements Listener {

    @EventHandler
    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        Block block = e.getDamager();
        eInfo.setTargetBlock(Optional.of(block));
        eInfo.setOldMaterialTargetBlock(Optional.of(block.getType()));
        if (!SCore.is1v12Less()) eInfo.setOldStatesTargetBlock(Optional.of(block.getBlockData().getAsString(true)));

        EventsManager.getInstance().activeOption(Option.ENTITY_DAMAGE_BY_BLOCK, eInfo, new ArrayList<>());
    }
}
