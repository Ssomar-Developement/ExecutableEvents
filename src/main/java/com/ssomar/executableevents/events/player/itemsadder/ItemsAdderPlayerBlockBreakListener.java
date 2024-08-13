package com.ssomar.executableevents.events.player.itemsadder;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.SsomarDev;
import com.ssomar.score.sobject.sactivator.EventInfo;
import dev.lone.itemsadder.api.Events.CustomBlockBreakEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class ItemsAdderPlayerBlockBreakListener implements Listener {

    @EventHandler
    public void onCustomBlockBreakEvent(CustomBlockBreakEvent e) {
        //SsomarDev.testMsg("ItemsAdder onCustomBlockBreakEvent", true);
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        eInfo.setTargetBlock(Optional.of(e.getBlock()));
        eInfo.setOldMaterialTargetBlock(Optional.of(e.getBlock().getType()));
        if (!SCore.is1v12Less()) eInfo.setOldStatesTargetBlock(Optional.of(e.getBlock().getBlockData().getAsString(true)));
        eInfo.setOption(Option.ITEMSADDER_PLAYER_BLOCK_BREAK);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
