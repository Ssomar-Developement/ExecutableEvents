package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.score.utils.emums.DetailedInteraction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerLeftClickEvent implements Listener {

    @EventHandler
    public void onPlayerRightClickEvent(com.ssomar.sevents.events.player.click.left.PlayerLeftClickEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        if (e.hasBlock()) {
            eInfo.setTargetBlock(Optional.of(e.getBlock()));
            eInfo.setOldMaterialTargetBlock(Optional.of(e.getBlock().getType()));
            if (!SCore.is1v12Less()) eInfo.setOldStatesTargetBlock(Optional.of(e.getBlock().getBlockData().getAsString(true)));
            eInfo.setDetailedInteraction(Optional.of(DetailedInteraction.BLOCK));
            eInfo.setBlockface(Optional.of(e.getBlockFace().name()));
        } else eInfo.setDetailedInteraction(Optional.of(DetailedInteraction.AIR));
        eInfo.setOption(Option.PLAYER_LEFT_CLICK);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
