package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;

import java.util.Optional;

public class PlayerTakeLecternBookListener implements Listener {

    @EventHandler
    public void onPlayerTakeLecternBookEvent(PlayerTakeLecternBookEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getPlayer().getWorld()));
        eInfo.setBlock(Optional.of(e.getLectern().getBlock()));
        eInfo.setOption(Option.PLAYER_TAKE_LECTERN_BOOK);
        EventsManager.getInstance().activeOption(eInfo);
    }

}
