package com.ssomar.executableevents.events.player.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ToDeleteTestMMOLIB implements Listener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {

        if (e.getDamager() instanceof Player) {

            Player damager = (Player) e.getDamager();

            if (e.getEntity() instanceof Player) {
                Player damagee = (Player) e.getEntity();

                System.out.println("Damager: "+damager.getName());
                System.out.println("Damagee: "+damagee.getName());
            }
        }
    }
}
