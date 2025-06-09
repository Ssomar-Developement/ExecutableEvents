package com.ssomar.executableevents.events.server;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServiceUnregisterEvent;

public class ServiceUnregisterListener implements Listener {

    @EventHandler
    public void ServiceUnregisterEvent(ServiceUnregisterEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.getPlaceholders().put("%getEventName%", e.getEventName());
        eInfo.getPlaceholders().put("%is_async%", String.valueOf(e.isAsynchronous()));
        eInfo.setOption(Option.SERVICE_UNREGISTER);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
