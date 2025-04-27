package com.ssomar.executableevents.events.vehicle;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;

import java.util.Optional;

public class VehicleCreateListener implements Listener {

    @EventHandler
    public void VehicleCreateEvent(VehicleCreateEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getVehicle().getWorld()));
        eInfo.setEntity(Optional.of(e.getVehicle()));
        eInfo.setOption(Option.VEHICLE_CREATE);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
