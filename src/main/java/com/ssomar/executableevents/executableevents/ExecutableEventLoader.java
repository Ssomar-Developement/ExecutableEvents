package com.ssomar.executableevents.executableevents;

import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.configs.GeneralConfig;
import com.ssomar.executableevents.configs.api.PlaceholderAPI;
import com.ssomar.executableevents.executableevents.manager.ExecutableEventsManager;
import com.ssomar.executableevents.utils.ZipUtility;
import com.ssomar.score.SCore;
import com.ssomar.score.events.loop.LoopManager;
import com.ssomar.score.sobject.NewSObjectLoader;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExecutableEventLoader extends NewSObjectLoader<ExecutableEvent> {

    private final static String DEFAULT = "Default";
    private static ExecutableEventLoader instance;
    @Getter
    @Setter
    private boolean createBackup;


    public ExecutableEventLoader() {
        super(ExecutableEvents.plugin, "/com/ssomar/executableevents/configs/events/", ExecutableEventsManager.getInstance(), 25);
        createBackup = false;
    }

    public static ExecutableEventLoader getInstance() {
        if (instance == null) instance = new ExecutableEventLoader();
        return instance;
    }

    @Override
    public void load() {
        LoopManager.getInstance().resetLoopActivatorsEE();
        ExecutableEventsManager.getInstance().setDefaultObjects(new ArrayList<>());
        //if (!GeneralConfig.getInstance().isDisableTestItems()) {
            if (PlaceholderAPI.isLotOfWork()) {
                this.loadDefaultPremiumObjects(this.getPremiumDefaultObjectsName());
            }
            this.loadDefaultEncodedPremiumObjects(this.getPremiumPackObjectsName());
        //}

        // ITEMS CONFIG
        ExecutableEventsManager.getInstance().setLoadedObjects(new ArrayList<>());

        this.resetCpt();
        File itemsDirectory;
        if ((itemsDirectory = new File(ExecutableEvents.getPluginSt().getDataFolder() + "/events")).exists()) {
            /* create backup at each restart / reload but not at /ei reload */
            if (createBackup && !GeneralConfig.getInstance().isDisableBackup()) {
                ZipUtility zipUtility = new ZipUtility();
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
                    LocalDateTime now = LocalDateTime.now();
                    new File(ExecutableEvents.getPluginSt().getDataFolder() + "/backups/" + dtf.format(now)).mkdirs();
                    zipUtility.zip(new String[]{itemsDirectory.getPath()}, ExecutableEvents.getPluginSt().getDataFolder() + "/backups/" + dtf.format(now) + "/backup.zip");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                createBackup = false;
            }

            this.loadObjectsInFolder(itemsDirectory, !PlaceholderAPI.isLotOfWork());
        } else {
            this.createDefaultObjectsFile(!PlaceholderAPI.isLotOfWork());
            this.load();
        }
    }

    public Map<String, List<String>> getPremiumPackObjectsName() {
        Map<String, List<String>> defaultItems = new HashMap<>();

        return defaultItems;
    }

    @Override
    public Map<String, List<String>> getPremiumDefaultObjectsName() {
        Map<String, List<String>> defaultItems = new HashMap<>();

        defaultItems.put(DEFAULT, new ArrayList<>());

        return defaultItems;
    }

    @Override
    public Map<String, List<String>> getFreeDefaultObjectsName() {
        Map<String, List<String>> defaultItems = new HashMap<>();

        defaultItems.put(DEFAULT, new ArrayList<>());

        return defaultItems;
    }

    @Override
    public void configVersionsConverter(File file) {

    }

    @Override
    public Optional<ExecutableEvent> getObject(FileConfiguration itemConfig, String id, boolean showError, boolean isPremiumLoading, String path) {

        List<String> errors = new ArrayList<>();
        ExecutableEvent item = new ExecutableEvent(id, path);
        errors.addAll(item.load(ExecutableEvents.plugin, itemConfig, isPremiumLoading));

        if (showError) {
            for (String s : errors) {
                ExecutableEvents.plugin.getServer().getLogger().severe(s);
            }
        }
        return Optional.ofNullable(item);
    }

}
