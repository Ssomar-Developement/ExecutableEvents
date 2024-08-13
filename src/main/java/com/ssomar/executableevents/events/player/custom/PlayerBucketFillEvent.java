package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerBucketFillEvent implements Listener {

    @EventHandler
    public void onPlayerBucketFillEvent(org.bukkit.event.player.PlayerBucketFillEvent e) {

        Player p = e.getPlayer();
        Block b = e.getBlockClicked();

        EventInfo eInfos = new EventInfo(e);
        eInfos.setPlayer(Optional.of(p));
        eInfos.setTargetBlock(Optional.of(e.getBlock()));
        eInfos.setOldMaterialTargetBlock(Optional.of(e.getBlock().getType()));
        if (!SCore.is1v12Less()) eInfos.setOldStatesTargetBlock(Optional.of(e.getBlock().getBlockData().getAsString(true)));
        eInfos.setOption(Option.PLAYER_FILL_BUCKET);
        EventsManager.getInstance().activeOption(eInfos);
    }
}
