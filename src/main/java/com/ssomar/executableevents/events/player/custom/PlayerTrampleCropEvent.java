package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerTrampleCropEvent implements Listener {

    @EventHandler
    public void onPlayerAllRightClickEvent(com.ssomar.sevents.events.player.tramplecrop.PlayerTrampleCropEvent event) {
        EventInfo eInfo = new EventInfo(event);
        eInfo.setPlayer(Optional.of(event.getPlayer()));
        eInfo.setTargetBlock(Optional.of(event.getBlock()));
        eInfo.setOldMaterialBlock(Optional.of(event.getBlock().getType()));
        if (!SCore.is1v12Less())
            eInfo.setOldStatesBlock(Optional.of(event.getBlock().getBlockData().getAsString(true)));
        EventsManager.getInstance().activeOption(Option.PLAYER_TRAMPLE_CROP, eInfo, new ArrayList<>());
    }

}
