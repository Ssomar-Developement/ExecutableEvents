package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.SCore;
import com.ssomar.score.events.BlockBreakEventExtension;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerBlockBreakEvent implements Listener {

    /* MUST HAVE A MINOR PRIORITY THAN BLOCK BREAK OF EB */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onBlockBreakEvent(BlockBreakEvent e) {
        boolean fromMineInCube = false;
        if (e instanceof BlockBreakEventExtension) {
            fromMineInCube = ((BlockBreakEventExtension) e).isFromCustomBreakCommand();
        }
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetBlock(Optional.of(e.getBlock()));
        eInfo.setOldMaterialTargetBlock(Optional.of(e.getBlock().getType()));
        if (!SCore.is1v12Less()) eInfo.setOldStatesTargetBlock(Optional.of(e.getBlock().getBlockData().getAsString(true)));
        eInfo.setFromCustomBreakCommand(fromMineInCube);
        EventsManager.getInstance().activeOption(Option.PLAYER_BLOCK_BREAK, eInfo, new ArrayList<>());
    }
}
