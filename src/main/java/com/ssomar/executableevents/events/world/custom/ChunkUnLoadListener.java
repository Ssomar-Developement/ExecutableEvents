package com.ssomar.executableevents.events.world.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

import java.util.Optional;

public class ChunkUnLoadListener implements Listener {

    @EventHandler
    public void chunkLoadEvent(ChunkUnloadEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getWorld()));
        eInfo.setOption(Option.CHUNK_LOAD);
        eInfo.getPlaceholders().put("%world%", e.getChunk().getWorld().getName());
        eInfo.getPlaceholders().put("%coord_x%", String.valueOf(e.getChunk().getX()));
        eInfo.getPlaceholders().put("%coord_y%", String.valueOf(e.getChunk().getZ()));
        eInfo.getPlaceholders().put("%isslimechunk%", String.valueOf(e.getChunk().isSlimeChunk()));
        eInfo.getPlaceholders().put("%isloaded%", String.valueOf(e.getChunk().isLoaded()));
        eInfo.getPlaceholders().put("%isgenerated%", String.valueOf(e.getChunk().isGenerated()));
        eInfo.getPlaceholders().put("%isforceloaded%", String.valueOf(e.getChunk().isForceLoaded()));
        eInfo.getPlaceholders().put("%issavechunk%", String.valueOf(e.isSaveChunk()));
        EventsManager.getInstance().activeOption(eInfo);
    }
}
