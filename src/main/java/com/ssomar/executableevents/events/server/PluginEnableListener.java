package com.ssomar.executableevents.events.server;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

public class PluginEnableListener implements Listener {

    @EventHandler
    public void PluginEnableEvent(PluginEnableEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.getPlaceholders().put("%plugin_name%", e.getPlugin().getName());
        eInfo.setOption(Option.PLUGIN_ENABLE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
