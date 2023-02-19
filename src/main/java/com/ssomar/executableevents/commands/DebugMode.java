package com.ssomar.executableevents.commands;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DebugMode {

    private static DebugMode instance;

    @Getter
    private List<Player> playersInDebugMode;

    @Getter
    @Setter
    private boolean enabledInConsole;

    public DebugMode() {
        playersInDebugMode = new ArrayList<>();
        enabledInConsole = false;
    }

    public static DebugMode getInstance() {
        if (instance == null) {
            instance = new DebugMode();
        }
        return instance;
    }
}
