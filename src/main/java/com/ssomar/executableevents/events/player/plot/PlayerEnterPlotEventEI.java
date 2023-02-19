package com.ssomar.executableevents.events.player.plot;

import com.google.common.eventbus.Subscribe;
import com.plotsquared.core.PlotAPI;
import com.plotsquared.core.events.PlayerEnterPlotEvent;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.score.sobject.sactivator.EventInfo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerEnterPlotEventEI {

    public PlayerEnterPlotEventEI() {
        PlotAPI api = new PlotAPI();
        api.registerListener(this);
    }

    @Subscribe
    public void playerEnterPlotEvent(PlayerEnterPlotEvent e) {
        Player p = Bukkit.getServer().getPlayer(e.getPlotPlayer().getUUID());
        if (p == null || e.getPlot().getOwner() == null || !e.getPlot().getOwner().equals(p.getUniqueId())) return;

        EventInfo eInfo = new EventInfo(null);
        eInfo.setPlayer(Optional.of(p));
        EventsManager.getInstance().activeOption(Option.PLAYER_ENTER_IN_HIS_PLOT, eInfo, new ArrayList<>());
    }
}