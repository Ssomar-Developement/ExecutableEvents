package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Optional;

public class PlayerInventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryOpenEvent(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;

        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of((Player) e.getWhoClicked()));
        eInfo.setInventoryType(Optional.of(e.getInventory().getType()));
        eInfo.getPlaceholders().put("%isShiftClick%", String.valueOf(e.getClick().isShiftClick()));
        eInfo.getPlaceholders().put("%isMouseClick%", String.valueOf(e.getClick().isMouseClick()));
        eInfo.getPlaceholders().put("%isLeftClick%", String.valueOf(e.getClick().isLeftClick()));
        eInfo.getPlaceholders().put("%isRightClick%", String.valueOf(e.getClick().isRightClick()));
        eInfo.getPlaceholders().put("%isKeyboardClick%", String.valueOf(e.getClick().isKeyboardClick()));
        eInfo.getPlaceholders().put("%isCreativeAction%", String.valueOf(e.getClick().isCreativeAction()));
        eInfo.getPlaceholders().put("%getAction%", String.valueOf(e.getAction()));
        eInfo.getPlaceholders().put("%beforeSlot%", String.valueOf(e.getSlot()));
        eInfo.getPlaceholders().put("%afterSlot%", String.valueOf(e.getRawSlot()));
        eInfo.setOption(Option.PLAYER_INVENTORY_CLICK);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
