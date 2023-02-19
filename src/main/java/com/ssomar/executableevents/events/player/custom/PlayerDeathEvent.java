package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerDeathEvent implements Listener {

    @EventHandler
    public void onPlayerDeathEvent(org.bukkit.event.entity.PlayerDeathEvent e) {
        Player p = e.getEntity();

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(p));
        if(p.getLastDamageCause() != null) eInfo.setDamageCause(Optional.of(p.getLastDamageCause().getCause()));
        else eInfo.setDamageCause(Optional.empty());
        EventsManager.getInstance().activeOption(Option.PLAYER_DEATH, eInfo, new ArrayList<>());
    }
}
