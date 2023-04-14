package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerDropItemEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerDropItemEvent(org.bukkit.event.player.PlayerDropItemEvent e) {

        Player p = e.getPlayer();

        /* PLAYER_DROP_ITEM */
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(p));

        // Force mainhand because we can't detect from which slot the item has been dropped
        eInfo.setForceMainHand(true);

        eInfo.setItem(Optional.of(e.getItemDrop().getItemStack()));

        EventsManager.getInstance().activeOption(Option.PLAYER_DROP_ITEM, eInfo, new ArrayList<>());

    }
}
