package com.ssomar.executableevents.events.block.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BellResonateEvent;

import java.util.Optional;

public class BellResonateListener implements Listener {
    @EventHandler
    public void BellResonateEvent(BellResonateEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setBlock(Optional.of(e.getBlock()));
        eInfo.setOption(Option.BELL_RESONATE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
