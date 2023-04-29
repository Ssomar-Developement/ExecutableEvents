package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerSendMessageEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChatEvent(AsyncPlayerChatEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setCommand(Optional.of(e.getMessage()));
        EventsManager.getInstance().activeOption(Option.PLAYER_SEND_MESSAGE, eInfo, new ArrayList<>());
    }
}
