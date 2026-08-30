package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerReceiveEffectListener implements Listener {

    @EventHandler
    public void playerEntityPotionEffectEvent(EntityPotionEffectEvent e) {

        if (e.getAction() != EntityPotionEffectEvent.Action.ADDED) return;

        // Bind to EntityEvent#getEntity() (returns Entity): the LivingEntity covariant override of
        // EntityPotionEffectEvent#getEntity() only exists in recent Paper APIs, calling it directly
        // throws NoSuchMethodError on 1.21.8 and older servers.
        Entity entity = ((EntityEvent) e).getEntity();
        if (!(entity instanceof Player)) return;

        Player p = (Player) entity;

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(p));
        eInfo.setEffect(Optional.of(e.getNewEffect()));
        eInfo.setOption(Option.PLAYER_RECEIVE_EFFECT);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
