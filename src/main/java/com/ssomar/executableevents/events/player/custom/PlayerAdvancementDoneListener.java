package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.Arrays;
import java.util.Optional;

public class PlayerAdvancementDoneListener implements Listener {

    @EventHandler
    public void onPlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        // 1.12 api doesn't return advancement's name
        if (SCore.is1v13Less())
            try {
                String advancement = "null";
                advancement = PlainTextComponentSerializer.plainText().serialize(e.getAdvancement().getDisplay().displayName());
                // remove the brackets
                advancement = advancement.substring(1, advancement.length() - 1);
                eInfo.getPlaceholders().put("%advancement%", advancement);
            } catch (Exception exception) {}
        eInfo.setOption(Option.PLAYER_ADVANCEMENT);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
