package com.ssomar.executableevents.events.server;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;

import java.util.Optional;

public class MapInitializeListener implements Listener {

    @EventHandler
    public void MapInitializeEvent(MapInitializeEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.ofNullable(e.getMap().getWorld()));
        eInfo.setOption(Option.MAP_INITIALIZE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
