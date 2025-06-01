package com.ssomar.executableevents.events.server;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServiceRegisterEvent;

public class ServiceRegisterListener implements Listener {

    @EventHandler
    public void ServiceRegisterEvent(ServiceRegisterEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.getPlaceholders().put("%getEventName%", e.getEventName());
        eInfo.getPlaceholders().put("%is_async%", String.valueOf(e.isAsynchronous()));
        eInfo.setOption(Option.SERVICE_REGISTER);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
