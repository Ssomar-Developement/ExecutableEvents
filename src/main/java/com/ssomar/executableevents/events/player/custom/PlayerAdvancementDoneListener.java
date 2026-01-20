package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.sobject.sactivator.EventInfo;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.Optional;

public class PlayerAdvancementDoneListener implements Listener {

    @EventHandler
    public void onPlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent e) {
        EventInfo eInfo = new EventInfo(e);
        eInfo.setPlayer(Optional.of(e.getPlayer()));
        try {
            String advancement = PlainTextComponentSerializer.plainText().serialize(e.getAdvancement().getDisplay().displayName());
            advancement = advancement.substring(1, advancement.length() - 1);
            eInfo.getPlaceholders().put("%advancement%", advancement);
        } catch (Exception exception) {}
        eInfo.setOption(Option.PLAYER_ADVANCEMENT);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
