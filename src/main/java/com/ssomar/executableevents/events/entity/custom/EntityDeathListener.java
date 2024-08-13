package com.ssomar.executableevents.events.entity.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.Optional;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent e) {
        Entity ent = e.getEntity();

        EventInfo eInfo = new EventInfo(e);
        eInfo.setEntity(Optional.of(ent));
        if(ent.getLastDamageCause() != null) eInfo.setDamageCause(Optional.of(ent.getLastDamageCause().getCause()));
        else eInfo.setDamageCause(Optional.empty());
        eInfo.setOption(Option.ENTITY_DEATH);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
