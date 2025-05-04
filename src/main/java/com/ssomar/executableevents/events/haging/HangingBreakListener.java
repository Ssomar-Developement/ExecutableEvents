package com.ssomar.executableevents.events.haging;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingBreakEvent;

import java.util.Optional;

public class HangingBreakListener implements Listener {
    @EventHandler
    public void HangingBreakEvent(HangingBreakEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getEntity().getWorld()));
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setOption(Option.HANGING_BREAK);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
