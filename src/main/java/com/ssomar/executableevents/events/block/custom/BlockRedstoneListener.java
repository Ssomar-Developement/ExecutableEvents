package com.ssomar.executableevents.events.block.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

import java.util.Optional;

public class BlockRedstoneListener implements Listener {
    @EventHandler
    public void BlockRedstoneEvent(BlockRedstoneEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setBlock(Optional.of(e.getBlock()));
        eInfo.setOption(Option.REDSTONE_BLOCK_ACTIVATION);
        eInfo.getPlaceholders().put("%newstate%", String.valueOf(e.getNewCurrent()));
        eInfo.getPlaceholders().put("%oldstate%", String.valueOf(e.getOldCurrent()));
        EventsManager.getInstance().activeOption(eInfo);
    }
}
