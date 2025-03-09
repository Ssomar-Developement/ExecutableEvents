package com.ssomar.executableevents.events.block.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

import java.util.Map;
import java.util.Optional;

public class EnchantItemListener implements Listener {
    @EventHandler
    public void EnchantItemEvent(EnchantItemEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getEnchanter()));
        eInfo.setBlock(Optional.of(e.getEnchantBlock()));
        eInfo.setItem(Optional.of(e.getItem()));
        eInfo.getPlaceholders().put("%enchants%", getEnchantPlaceholderFormat(e.getEnchantsToAdd()));
        eInfo.getPlaceholders().put("%levelCost%", String.valueOf(e.getExpLevelCost()));
        eInfo.setOption(Option.PLAYER_ENCHANT_ITEM);
        EventsManager.getInstance().activeOption(eInfo);
    }

    // Formating Enchants to String Ex: "Sharpness;10:Efficiency;2"
    private static String getEnchantPlaceholderFormat(Map<Enchantment, Integer> enchants) {
        if (enchants == null || enchants.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
            if (sb.length() > 0) {
                sb.append(":");
            }
            sb.append(entry.getKey().getKey().getKey()).append(";").append(entry.getValue());
        }

        return sb.toString();
    }
}
