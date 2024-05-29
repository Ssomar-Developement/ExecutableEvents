package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SsomarDev;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.sevents.events.player.equip.armor.PlayerEquipArmorEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerUnequipArmorListener implements Listener {

    @EventHandler
    public void playerEquipArmorEvent(PlayerEquipArmorEvent e) {

        if (e.getOldArmorPiece() == null || e.getOldArmorPiece().getType() == Material.AIR) return;
        //SsomarDev.testMsg("OldArmor > "+e.getOldArmorPiece(), true);
        Player p = e.getPlayer();

        EventInfo eInfo = new EventInfo(e);

        eInfo.setPlayer(Optional.of(p));
        EventsManager.getInstance().activeOption(Option.PLAYER_UNEQUIP_ARMOR, eInfo, new ArrayList<>());
    }
}
