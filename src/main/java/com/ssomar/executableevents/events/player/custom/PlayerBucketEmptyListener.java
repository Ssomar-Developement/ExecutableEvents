package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketEntityEvent;

import java.util.Optional;

public class PlayerBucketEmptyListener implements Listener {

    @EventHandler
    public void onPlayerBucketEmptyEvent(PlayerBucketEmptyEvent e) {

        Player p = e.getPlayer();
        Block b;

        //Refers to 1.19.2+
        if (SCore.is1v19v4Plus()) b = e.getBlock();
        else b = e.getBlockClicked().getRelative(e.getBlockFace());

        EventInfo eInfos = new EventInfo(e);
        eInfos.setPlayer(Optional.of(p));
        eInfos.setTargetBlock(Optional.of(b));
        eInfos.setOldMaterialTargetBlock(Optional.of(b.getType()));
        if (!SCore.is1v12Less()) eInfos.setOldStatesTargetBlock(Optional.of(b.getBlockData().getAsString(true)));
        eInfos.setOption(Option.PLAYER_EMPTY_BUCKET);
        EventsManager.getInstance().activeOption(eInfos);
    }
}
