package com.ssomar.executableevents.events;

import com.ssomar.executableevents.configs.GeneralConfig;
import com.ssomar.executableevents.executableevents.ExecutableEvent;
import com.ssomar.executableevents.executableevents.manager.ExecutableEventsManager;
import com.ssomar.score.SsomarDev;
import com.ssomar.score.features.custom.activators.activator.SActivator;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.score.usedapi.AllWorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Optional;

public class EventsManager {

    private static final Boolean DEBUG = false;
    private static EventsManager instance;
    private ExecutableEventsManager iM = ExecutableEventsManager.getInstance();

    public static EventsManager getInstance() {
        if (instance == null) instance = new EventsManager();
        return instance;
    }

    public void activeOptionAllPlayer(EventInfo eInfo) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            eInfo.setPlayer(Optional.of(p));
            eInfo.setWorld(Optional.of(p.getWorld()));
            activeOption(eInfo);
        }
    }

    public void activeOption(EventInfo eInfo) {

        SsomarDev.testMsg("activeOption", DEBUG);
        for (ExecutableEvent executableEvent : ExecutableEventsManager.getInstance().getAllObjects()) {

            if (!executableEvent.getEnabled().getValue()) continue;

            World world = Bukkit.getWorlds().get(0);
            if (eInfo.getPlayer().isPresent()) world = eInfo.getPlayer().get().getWorld();

            if (!isValidWorld(eInfo.getWorld().orElse(world), executableEvent)) continue;

            SsomarDev.testMsg("activeOption - isValidWorld >> " + executableEvent.getId(), DEBUG);

            SsomarDev.testMsg("activeOption >> " + executableEvent.getId(), DEBUG);

            for (SActivator activator : executableEvent.getActivators().getActivators(eInfo.getOption(), eInfo.getWhitelistActivatorsId(), eInfo.getWhitelistActivators())) {
                SsomarDev.testMsg("activeOption - activator >> " + activator.getId(), DEBUG);
                activator.runWithException(executableEvent, eInfo);
            }
        }
    }

    public boolean isValidWorld(World actual, ExecutableEvent item) {

        for (String str : GeneralConfig.getInstance().getDisableWorlds()) {
            Optional<World> worldOptional = AllWorldManager.getWorld(str);
            if (worldOptional.isPresent()) {
                World world = worldOptional.get();
                if (world == actual) return false;
            }
        }
        for (String str : item.getDisabledWorlds().getValue()) {
            Optional<World> worldOptional = AllWorldManager.getWorld(str);
            if (worldOptional.isPresent()) {
                World world = worldOptional.get();
                if (world == actual) return false;
            }
        }
        return true;
    }

}
