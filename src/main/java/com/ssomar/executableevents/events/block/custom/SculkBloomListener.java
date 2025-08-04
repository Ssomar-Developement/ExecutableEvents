package com.ssomar.executableevents.events.block.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SculkBloomEvent;

import java.util.Optional;

public class SculkBloomListener implements Listener {
    @EventHandler
    public void SculkBloomEvent(SculkBloomEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setBlock(Optional.of(e.getBlock()));
        eInfo.getPlaceholders().put("%charge%", String.valueOf(e.getCharge()));
        eInfo.setOption(Option.SCULK_BLOOM);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
