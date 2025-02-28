package com.ssomar.executableevents;

import com.ssomar.executableevents.configs.GeneralConfig;
import com.ssomar.executableevents.configs.api.PlaceholderAPI;
import com.ssomar.executableevents.executableevents.ExecutableEvent;
import com.ssomar.score.config.Config;
import com.ssomar.score.sobject.SObject;
import com.ssomar.score.splugin.SPlugin;

import static com.ssomar.executableevents.ExecutableEvents.NAME;
import static com.ssomar.executableevents.ExecutableEvents.NAME_COLOR;

public class SExecutableEvents implements SPlugin {

    private final ExecutableEvents plugin;

    public SExecutableEvents(ExecutableEvents plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getNameDesign() {
        return NAME_COLOR;
    }

    @Override
    public String getNameDesignWithBrackets() {
        return "&d[" + NAME_COLOR + "]";
    }

    @Override
    public String getObjectName() {
        return "events";
    }

    @Override
    public String getObjectNameForPermission(SObject sObject) {
        if(sObject instanceof ExecutableEvent) return "event";
        return "event";
    }

    @Override
    public ExecutableEvents getPlugin() {
        return plugin;
    }


    @Override
    public String getShortName() {
        return "EE";
    }

    @Override
    public String getName() {
        return NAME_COLOR;
    }

    @Override
    public String getNameWithBrackets() {
        return "["+NAME+"]";
    }


    @Override
    public boolean isLotOfWork() {
        return PlaceholderAPI.isLotOfWork();
    }

    @Override
    public int getMaxSObjectsLimit() {
        return 8;
    }

    @Override
    public Config getPluginConfig() {
        return GeneralConfig.getInstance();
    }
}
