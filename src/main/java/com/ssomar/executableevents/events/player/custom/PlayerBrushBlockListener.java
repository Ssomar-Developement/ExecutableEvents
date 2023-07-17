package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerBrushBlockListener implements Listener {


    @EventHandler(priority = EventPriority.HIGH)
    public void playerBlockDropItemEvent(BlockDropItemEvent e) {
        Block block = e.getBlock();
        Material m = block.getType();
        if (!m.toString().contains("SUSPICIOUS")) return;
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetBlock(Optional.of(e.getBlock()));
        eInfo.setOldMaterialTargetBlock(Optional.of(e.getBlock().getType()));
        if (!SCore.is1v12Less()) eInfo.setOldStatesTargetBlock(Optional.of(e.getBlock().getBlockData().getAsString(true)));
        EventsManager.getInstance().activeOption(Option.PLAYER_BRUSH_BLOCK, eInfo, new ArrayList<>());
    }
}
