package com.ssomar.executableevents.events.haging;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.hanging.HangingPlaceEvent;

import java.util.Optional;

public class HangingPlaceListener implements Listener {
    @EventHandler
    public void HangingPlaceEvent(HangingPlaceEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getEntity().getWorld()));
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.setOption(Option.HANGING_PLACE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
