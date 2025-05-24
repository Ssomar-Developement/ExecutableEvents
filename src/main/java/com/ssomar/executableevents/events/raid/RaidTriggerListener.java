package com.ssomar.executableevents.events.raid;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidTriggerEvent;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class RaidTriggerListener implements Listener {

    @EventHandler
    public void RaidTriggerEvent(RaidTriggerEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getWorld()));
        eInfo.setBlock(Optional.of(e.getWorld().getSpawnLocation().getBlock()));
        eInfo.getPlaceholders().put("%player%", e.getPlayer().toString());
        eInfo.getPlaceholders().put("%badomen_level%", String.valueOf(e.getRaid().getBadOmenLevel()));
        eInfo.setOption(Option.RAID_TRIGGER);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
