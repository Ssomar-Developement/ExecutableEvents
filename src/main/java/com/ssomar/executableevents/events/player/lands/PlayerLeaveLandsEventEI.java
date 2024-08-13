package com.ssomar.executableevents.events.player.lands;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import me.angeschossen.lands.api.events.player.PlayerAreaLeaveEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerLeaveLandsEventEI implements Listener {

    @EventHandler
    public void playerAreaEnterEvent(PlayerAreaLeaveEvent e) {
        Player p = e.getLandPlayer().getPlayer();
        boolean isValid = e.getArea().getOwnerUID().equals(p.getUniqueId()) || e.getArea().isTrusted(p.getUniqueId());
        if (p == null || !isValid) return;

        EventInfo eInfo = new EventInfo(null);
        eInfo.setPlayer(Optional.of(p));
        eInfo.setOption(Option.PLAYER_LEAVE_HIS_LAND);
        EventsManager.getInstance().activeOption(eInfo);
    }
}