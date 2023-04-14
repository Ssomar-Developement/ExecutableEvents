package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFertilizeEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerFertilizeBlockEvent implements Listener {

    @EventHandler
    public void onBlockFertilizeEvent(BlockFertilizeEvent e) {
        if (e.getPlayer() != null) {
            EventInfo eInfo = new EventInfo(e);
            eInfo.setPlayer(Optional.of(e.getPlayer()));
            eInfo.setTargetBlock(Optional.of(e.getBlock()));
            eInfo.setOldMaterialTargetBlock(Optional.of(e.getBlock().getType()));
            if (!SCore.is1v12Less()) eInfo.setOldStatesTargetBlock(Optional.of(e.getBlock().getBlockData().getAsString(true)));
            EventsManager.getInstance().activeOption(Option.PLAYER_FERTILIZE_BLOCK, eInfo, new ArrayList<>());
        }
    }

}
