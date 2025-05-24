package com.ssomar.executableevents.events.server;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.BroadcastMessageEvent;

import java.util.Optional;

public class BroadcastMessageListener implements Listener {

    @EventHandler
    public void BroadcastMessageEvent(BroadcastMessageEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.getPlaceholders().put("%message%", String.valueOf(e.message()));
        eInfo.getPlaceholders().put("%is_async%", String.valueOf(e.isAsynchronous()));
        eInfo.setOption(Option.BROADCAST_MESSAGE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
