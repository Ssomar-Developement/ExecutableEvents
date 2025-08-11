package com.ssomar.executableevents.events.block.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CauldronLevelChangeEvent;

import java.util.Optional;

public class CauldronLevelChangeListener implements Listener {
    @EventHandler
    public void CauldronLevelChangeEvent(CauldronLevelChangeEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setBlock(Optional.of(e.getBlock()));
        eInfo.setOption(Option.CAULDRON_LEVEL_CHANGE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
