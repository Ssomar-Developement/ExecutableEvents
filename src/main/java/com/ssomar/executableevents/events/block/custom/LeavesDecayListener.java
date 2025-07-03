package com.ssomar.executableevents.events.block.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;

import java.util.Optional;

public class LeavesDecayListener implements Listener {
    @EventHandler
    public void onLeavesDecayEvent(LeavesDecayEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setBlock(Optional.of(e.getBlock()));
        eInfo.setOption(Option.LEAVES_DECAY);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
