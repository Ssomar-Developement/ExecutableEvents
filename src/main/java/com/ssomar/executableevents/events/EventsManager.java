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
        // Resolved once: the world does not depend on the ExecutableEvent being tested
        World world = resolveWorld(eInfo);
        for (ExecutableEvent executableEvent : ExecutableEventsManager.getInstance().getAllObjects()) {

            if (!executableEvent.getEnabled().getValue()) continue;

            if (!isValidWorld(world, executableEvent)) continue;

            SsomarDev.testMsg("activeOption - isValidWorld >> " + executableEvent.getId(), DEBUG);

            SsomarDev.testMsg("activeOption >> " + executableEvent.getId(), DEBUG);

            for (SActivator activator : executableEvent.getActivators().getActivators(eInfo.getOption(), eInfo.getWhitelistActivatorsId(), eInfo.getWhitelistActivators())) {
                SsomarDev.testMsg("activeOption - activator >> " + activator.getId(), DEBUG);
                activator.runWithException(executableEvent, eInfo);
            }
        }
    }

    /**
     * Derives the world an event happened in, so that disabledWorlds is honored even by
     * activators whose listener never calls setPlayer/setWorld (ENTITY_TARGET_PLAYER,
     * ENTITY_TARGET_ENTITY, most custom entity/block events...). Order: explicit world,
     * player, entity, block, target player, target entity, target block, projectile,
     * and only then the default world (server-wide events that have no world at all).
     */
    public static World resolveWorld(EventInfo eInfo) {
        if (eInfo.getWorld() != null && eInfo.getWorld().isPresent()) return eInfo.getWorld().get();
        if (eInfo.getPlayer() != null && eInfo.getPlayer().isPresent()) return eInfo.getPlayer().get().getWorld();
        if (eInfo.getEntity() != null && eInfo.getEntity().isPresent()) return eInfo.getEntity().get().getWorld();
        if (eInfo.getBlock() != null && eInfo.getBlock().isPresent()) return eInfo.getBlock().get().getWorld();
        if (eInfo.getTargetPlayer() != null && eInfo.getTargetPlayer().isPresent()) return eInfo.getTargetPlayer().get().getWorld();
        if (eInfo.getTargetEntity() != null && eInfo.getTargetEntity().isPresent()) return eInfo.getTargetEntity().get().getWorld();
        if (eInfo.getTargetBlock() != null && eInfo.getTargetBlock().isPresent()) return eInfo.getTargetBlock().get().getWorld();
        if (eInfo.getProjectile() != null && eInfo.getProjectile().isPresent()) return eInfo.getProjectile().get().getWorld();
        return Bukkit.getWorlds().get(0);
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
