package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerHarvestBlockEvent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerHarvestBlockListener implements Listener {

    @EventHandler
    public void onlayerHarvestBlockEvent(PlayerHarvestBlockEvent e) {

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetBlock(Optional.of(e.getHarvestedBlock()));
        eInfo.setOldMaterialTargetBlock(Optional.of(e.getHarvestedBlock().getType()));
        if (!SCore.is1v12Less()) eInfo.setOldStatesTargetBlock(Optional.of(e.getHarvestedBlock().getBlockData().getAsString(true)));
        EventsManager.getInstance().activeOption(Option.PLAYER_HARVEST_BLOCK, eInfo, new ArrayList<>());
    }
}
