package com.ssomar.executableevents.events.raid;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Raider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidSpawnWaveEvent;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RaidWaveListener implements Listener {

    @EventHandler
    public void RaidSpawnWaveEvent(RaidSpawnWaveEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getWorld()));
        eInfo.setBlock(Optional.of(e.getWorld().getSpawnLocation().getBlock()));
        eInfo.getPlaceholders().put("%raiders%", UUID_WORKER(e.getRaiders()));
        eInfo.setOption(Option.RAID_WAVE);
        EventsManager.getInstance().activeOption(eInfo);
    }

    public static String UUID_WORKER(List<Raider> uuidList) {
        return uuidList.stream().map(raider -> raider.getUniqueId().toString()).collect(Collectors.joining(","));
    }
}
