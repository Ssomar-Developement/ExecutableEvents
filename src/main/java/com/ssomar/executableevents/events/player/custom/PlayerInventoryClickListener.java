package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
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
        eInfo.getPlaceholders().put("%is_shift_click%", String.valueOf(e.getClick().isShiftClick()));
        eInfo.getPlaceholders().put("%is_mouse_click%", String.valueOf(e.getClick().isMouseClick()));
        eInfo.getPlaceholders().put("%is_left_click%", String.valueOf(e.getClick().isLeftClick()));
        eInfo.getPlaceholders().put("%is_right_click%", String.valueOf(e.getClick().isRightClick()));
        eInfo.getPlaceholders().put("%is_keyboard_click%", String.valueOf(e.getClick().isKeyboardClick()));
        eInfo.getPlaceholders().put("%is_creative_action%", String.valueOf(e.getClick().isCreativeAction()));
        eInfo.getPlaceholders().put("%get_action%", String.valueOf(e.getAction()));
        eInfo.getPlaceholders().put("%before_slot%", String.valueOf(e.getSlot()));
        eInfo.getPlaceholders().put("%after_slot%", String.valueOf(e.getRawSlot()));
        eInfo.getPlaceholders().put("%inventory_type%", String.valueOf(e.getInventory().getType()));
        // someone made a complaint about how its failing at purpur 1.20.6 due to somehow InventoryView not existing in older versions
        // i straight up do not know when it got changed from abstract class to interface
        if (SCore.is1v21()) {
            eInfo.getPlaceholders().put("%inventory_title%", (e.getView().getTitle()));
        }
        eInfo.setOption(Option.PLAYER_INVENTORY_CLICK);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
