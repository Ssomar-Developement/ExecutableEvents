package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerFoodChange implements Listener {

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of((Player) e.getEntity()));
        eInfo.setOption(Option.PLAYER_FOOD_CHANGE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
