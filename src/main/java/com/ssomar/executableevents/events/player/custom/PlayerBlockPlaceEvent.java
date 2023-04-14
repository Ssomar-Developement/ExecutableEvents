package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerBlockPlaceEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockPlaceEvent(BlockPlaceEvent e) {

        Player p = e.getPlayer();
        Block b = e.getBlockPlaced();

        EventInfo eInfo = new EventInfo(e);

        eInfo.setPlayer(Optional.of(p));
        eInfo.setTargetBlock(Optional.of(e.getBlock()));
        eInfo.setOldMaterialTargetBlock(Optional.of(e.getBlock().getType()));
        if (!SCore.is1v12Less()) eInfo.setOldStatesTargetBlock(Optional.of(e.getBlock().getBlockData().getAsString(true)));
        EventsManager.getInstance().activeOption(Option.PLAYER_BLOCK_PLACE, eInfo, new ArrayList<>());
    }
}
