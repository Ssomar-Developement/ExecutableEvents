package com.ssomar.executableevents.events.block.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CrafterCraftEvent;

import java.util.Optional;

public class CrafterCraftListener implements Listener {
    @EventHandler
    public void onCrafterCraftEvent(CrafterCraftEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setBlock(Optional.of(e.getBlock()));
        eInfo.getPlaceholders().put("%result%", String.valueOf(e.getResult().getType()));
        eInfo.getPlaceholders().put("%amount%", String.valueOf(e.getResult().getAmount()));
        eInfo.setOption(Option.CRAFTER_CRAFT);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
