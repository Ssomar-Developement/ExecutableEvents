package com.ssomar.executableevents.events.block.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.TNTPrimeEvent;

import java.util.Optional;

public class TNTPrimeListener implements Listener {
    @EventHandler
    public void TNTPrimeEvent(TNTPrimeEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setBlock(Optional.of(e.getBlock()));
        eInfo.setOption(Option.TNT_PRIME);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
