package com.ssomar.executableevents.events.world.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.SpawnChangeEvent;
import org.bukkit.event.world.StructureGrowEvent;

import java.util.Optional;

public class StructureGrowListener implements Listener {

    @EventHandler
    public void onStructureGrowEvent(StructureGrowEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getWorld()));
        eInfo.setBlock(Optional.of(e.getLocation().getBlock()));
        eInfo.setOption(Option.STRUCTURE_GROW);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
