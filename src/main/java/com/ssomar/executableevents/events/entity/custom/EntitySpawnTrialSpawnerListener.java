package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.block.TrialSpawner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.TrialSpawnerSpawnEvent;

import java.util.Optional;

public class EntitySpawnTrialSpawnerListener implements Listener {

    @EventHandler
    public void onTrialSpawnerEvent(TrialSpawnerSpawnEvent e) {
        TrialSpawner spawner = e.getTrialSpawner();

        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(e.getEntity()));
        eInfo.getPlaceholders().put("%is_ominous%", String.valueOf(spawner.isOminous()));
        eInfo.setOption(Option.ENTITY_SPAWN_TRIALSPAWNER);
        EventsManager.getInstance().activeOption(eInfo);
    }
}