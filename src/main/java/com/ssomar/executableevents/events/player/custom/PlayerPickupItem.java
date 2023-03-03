package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerPickupItem implements Listener {

    @EventHandler
    public void onPlayerPickupItem(EntityPickupItemEvent e) {
        if(!(e.getEntity() instanceof Player)) return;

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(((Player) e.getEntity()).getPlayer()));

        EventsManager.getInstance().activeOption(Option.PLAYER_PICKUP_ITEM, eInfo, new ArrayList<>());
    }
}
