package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPlaceEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerEntityPlaceListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityPlaceEvent(EntityPlaceEvent e) {

        Player p = e.getPlayer();
        Entity entity = e.getEntity();

        EventInfo eInfo = new EventInfo(e);

        eInfo.setPlayer(Optional.of(p));
        eInfo.setTargetEntity(Optional.of(entity));
        eInfo.setOption(Option.PLAYER_ENTITY_PLACE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
