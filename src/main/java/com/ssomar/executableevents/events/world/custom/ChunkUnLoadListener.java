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
    public void chunkUnLoadEvent(ChunkUnloadEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getWorld()));
        eInfo.setOption(Option.CHUNK_UNLOAD);
        eInfo.getPlaceholders().put("%world%", e.getChunk().getWorld().getName());
        eInfo.getPlaceholders().put("%coord_x%", String.valueOf(e.getChunk().getX()));
        eInfo.getPlaceholders().put("%coord_z%", String.valueOf(e.getChunk().getZ()));
        eInfo.getPlaceholders().put("%is_slime_chunk%", String.valueOf(e.getChunk().isSlimeChunk()));
        eInfo.getPlaceholders().put("%is_loaded%", String.valueOf(e.getChunk().isLoaded()));
        eInfo.getPlaceholders().put("%is_generated%", String.valueOf(e.getChunk().isGenerated()));
        eInfo.getPlaceholders().put("%is_force_loaded%", String.valueOf(e.getChunk().isForceLoaded()));
        eInfo.getPlaceholders().put("%is_save_chunk%", String.valueOf(e.isSaveChunk()));
        EventsManager.getInstance().activeOption(eInfo);
    }
}
