package com.ssomar.executableevents.events.server;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.RemoteServerCommandEvent;

public class RemoteServerCommandListener implements Listener {

    @EventHandler
    public void RemoteServerCommandEvent(RemoteServerCommandEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.getPlaceholders().put("%command%", e.getCommand());
        eInfo.getPlaceholders().put("%is_async%", String.valueOf(e.isAsynchronous()));
        eInfo.setOption(Option.REMOTE_SERVER_COMMAND);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
