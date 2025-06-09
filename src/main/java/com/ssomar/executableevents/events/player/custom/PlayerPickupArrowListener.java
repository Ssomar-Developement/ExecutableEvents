package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupArrowEvent;

import java.util.Optional;

public class PlayerPickupArrowListener implements Listener {

    @EventHandler
    public void onPlayerPickupArrowEvent(PlayerPickupArrowEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setItem(Optional.of(e.getItem().getItemStack()));
        eInfo.setOption(Option.PLAYER_PICKUP_ARROW);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
