package com.ssomar.executableevents.events;

import com.ssomar.executableevents.configs.GeneralConfig;
import com.ssomar.executableevents.executableevents.ExecutableEvent;
import com.ssomar.executableevents.executableevents.activators.ActivatorEEFeature;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.executableevents.manager.ExecutableEventsManager;
import com.ssomar.score.SsomarDev;
import com.ssomar.score.features.custom.activators.activator.NewSActivator;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.score.sobject.sactivator.SOption;
import com.ssomar.score.usedapi.AllWorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class EventsManager {

    private static final Boolean DEBUG = false;
    private static EventsManager instance;
    private ExecutableEventsManager iM = ExecutableEventsManager.getInstance();

    public static EventsManager getInstance() {
        if (instance == null) instance = new EventsManager();
        return instance;
    }

    public void activeOptionAllPlayer(SOption o, EventInfo eInfo, List<ActivatorEEFeature> optionalWhitelist) {

       for (Player p : Bukkit.getOnlinePlayers()) {
            eInfo.setPlayer(Optional.of(p));
            eInfo.setWorld(Optional.of(p.getWorld()));
            activeOption(o, eInfo, optionalWhitelist);
        }
    }

    public void activeOption(SOption o, EventInfo eInfo, List<ActivatorEEFeature> optionalWhitelist) {

        SsomarDev.testMsg("activeOption", DEBUG);
        for (ExecutableEvent executableEvent : ExecutableEventsManager.getInstance().getAllObjects()) {
            if(!executableEvent.getEnabled().getValue()) continue;

            World world = Bukkit.getWorlds().get(0);
            if(eInfo.getPlayer().isPresent()) world = eInfo.getPlayer().get().getWorld();

            if (!isValidWorld(eInfo.getWorld().orElse(world), executableEvent)) continue;

            SsomarDev.testMsg("activeOption - isValidWorld >> "+executableEvent.getId(), DEBUG);


            for (NewSActivator activator : executableEvent.getActivators().getActivators().values()) {
                if(!optionalWhitelist.isEmpty()) {
                    if (activator.getOption().isLoopOption()) {
                        boolean valid = false;
                        for (ActivatorEEFeature activatorEE : optionalWhitelist) {
                            if (activatorEE.isEqualsOrAClone(activator)) {
                                valid = true;
                            }
                        }
                        if (!valid) continue;
                    }
                }
                if (activator.getOption().equals(o)) {
                    SsomarDev.testMsg("activeOption - activator.getOption().equals(o)", DEBUG);
                    activator.run(executableEvent, eInfo);
                }
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
