package com.ssomar.executableevents.events.player.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
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
        String advancement = "null";
        // getDisplay() only got added at 1.19 api
        if (SCore.is1v19Plus()) {
            try {
                advancement = PlainTextComponentSerializer.plainText().serialize(e.getAdvancement().getDisplay().displayName());
                // remove the brackets
                advancement = advancement.substring(1, advancement.length() - 1);
            } catch (Exception exception) {
                // should never happen
                SCore.plugin.getLogger().info(exception.getStackTrace().toString());
            }
        }
        eInfo.getPlaceholders().put("%advancement%", advancement);
        eInfo.setOption(Option.PLAYER_ADVANCEMENT);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
