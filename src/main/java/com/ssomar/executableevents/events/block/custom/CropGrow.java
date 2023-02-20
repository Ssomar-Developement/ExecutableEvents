package com.ssomar.executableevents.events.block.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;

import java.util.ArrayList;
import java.util.Optional;

public class CropGrow implements Listener {

    @EventHandler
    public void onCropGrow(org.bukkit.event.block.BlockGrowEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setTargetBlock(Optional.of(e.getBlock()));
        eInfo.setOldMaterialBlock(Optional.of(e.getBlock().getType()));
        if (!SCore.is1v12Less()) eInfo.setOldStatesBlock(Optional.of(e.getBlock().getBlockData().getAsString(true)));
        EventsManager.getInstance().activeOption(Option.CROP_GROW, eInfo, new ArrayList<>());
    }
}
