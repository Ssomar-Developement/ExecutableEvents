package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerOpenInventoryListener implements Listener {

    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent e) {

        if (!(e.getPlayer() instanceof Player)) return;

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of((Player) e.getPlayer()));
        EventsManager.getInstance().activeOption(Option.PLAYER_OPEN_INVENTORY, eInfo, new ArrayList<>());
    }
}
