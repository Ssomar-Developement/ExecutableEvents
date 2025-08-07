package com.ssomar.executableevents.events.block.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BrewingStartEvent;

import java.util.Optional;

public class BrewingStartListener implements Listener {
    @EventHandler
    public void BrewingStartEvent(BrewingStartEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setBlock(Optional.of(e.getBlock()));
        eInfo.getPlaceholders().put("%brewing_time%", String.valueOf(e.getBrewingTime()));
        eInfo.getPlaceholders().put("%recipe_time%", String.valueOf(e.getRecipeBrewTime()));
        eInfo.setOption(Option.BREWING_START);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
