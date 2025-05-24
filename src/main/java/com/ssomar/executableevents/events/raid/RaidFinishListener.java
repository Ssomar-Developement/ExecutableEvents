package com.ssomar.executableevents.events.raid;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidFinishEvent;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class RaidFinishListener implements Listener {

    @EventHandler
    public void RaidFinishEvent(RaidFinishEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getWorld()));
        eInfo.setBlock(Optional.of(e.getWorld().getSpawnLocation().getBlock()));
        eInfo.getPlaceholders().put("%badomen_level%", String.valueOf(e.getRaid().getBadOmenLevel()));
        eInfo.getPlaceholders().put("%heroes%", String.valueOf(UUID_WORKER(e.getRaid().getHeroes())));
        eInfo.setOption(Option.RAID_FINISH);
        EventsManager.getInstance().activeOption(eInfo);
    }

    public static String UUID_WORKER(Set<UUID> uuidList) {
        return uuidList.stream().map(UUID::toString).collect(Collectors.joining(","));
    }
}
