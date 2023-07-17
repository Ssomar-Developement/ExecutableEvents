package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerReceiveEffectListener implements Listener {

    @EventHandler
    public void playerEntityPotionEffectEvent(EntityPotionEffectEvent e) {

        if (e.getAction() != EntityPotionEffectEvent.Action.ADDED) return;

        if (!(e.getEntity() instanceof Player)) return;

        Player p = (Player) e.getEntity();

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(p));
        eInfo.setEffect(Optional.of(e.getNewEffect()));
        EventsManager.getInstance().activeOption(Option.PLAYER_RECEIVE_EFFECT, eInfo, new ArrayList<>());
    }
}
