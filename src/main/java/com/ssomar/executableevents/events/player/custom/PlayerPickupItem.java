package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerPickupItem implements Listener {

    @EventHandler
    public void onPlayerPickupItem(EntityPickupItemEvent e) {
        if(!(e.getEntity() instanceof Player)) return;

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(((Player) e.getEntity()).getPlayer()));
        eInfo.setItem(Optional.of(e.getItem().getItemStack()));
        eInfo.setTargetEntity(Optional.of(e.getItem()));
        eInfo.getPlaceholders().put("%item_type%", e.getItem().getItemStack().getType().toString());
        eInfo.getPlaceholders().put("%item_amount%", String.valueOf(e.getItem().getItemStack().getAmount()));

        CustomModelDataComponent cmdataComp = e.getItem().getItemStack().getItemMeta().getCustomModelDataComponent();

        if (SCore.is1v21v4Plus()) {
            eInfo.getPlaceholders().put("%item_cmdata_f_0%", cmdataComp.getFloats().size() > 0 ? cmdataComp.getFloats().getFirst().toString() : "-1");
            eInfo.getPlaceholders().put("%item_cmdata_s_0%", cmdataComp.getStrings().size() > 0 ? cmdataComp.getStrings().getFirst() : "null");
        } else {
            eInfo.getPlaceholders().put("%item_cmdata%", String.valueOf(e.getItem().getItemStack().getItemMeta().getCustomModelData()));
        }

        eInfo.setOption(Option.PLAYER_PICKUP_ITEM);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
