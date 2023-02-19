package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
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
        eInfo.setTargetBlock(Optional.of(e.getDamager()));
        eInfo.setOldMaterialTargetBlock(Optional.of(e.getDamager().getType()));
        if (!SCore.is1v12Less())
            eInfo.setOldStatesTargetBlock(Optional.of(e.getDamager().getBlockData().getAsString(true)));
        EventsManager.getInstance().activeOption(Option.ENTITY_DAMAGE_BY_BLOCK, eInfo, new ArrayList<>());
    }
}
