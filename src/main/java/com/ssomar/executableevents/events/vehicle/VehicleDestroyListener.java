package com.ssomar.executableevents.events.vehicle;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleDestroyEvent;

import java.util.Optional;

public class VehicleDestroyListener implements Listener {

    @EventHandler
    public void VehicleDestroyEvent(VehicleDestroyEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setWorld(Optional.of(e.getVehicle().getWorld()));
        eInfo.setEntity(Optional.of(e.getVehicle()));
        eInfo.setOption(Option.VEHICLE_DESTROY);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
