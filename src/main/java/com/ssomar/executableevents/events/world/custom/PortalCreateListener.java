package com.ssomar.executableevents.events.world.custom;

import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

import java.util.Optional;

public class PortalCreateListener implements Listener {

    @EventHandler
    public void onPortalCreate(PortalCreateEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getWorld()));
        eInfo.setBlock(Optional.of(e.getBlocks().get(0).getBlock()));

    }
}
