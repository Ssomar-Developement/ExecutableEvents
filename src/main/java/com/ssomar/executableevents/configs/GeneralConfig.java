package com.ssomar.executableevents.configs;

import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.score.features.custom.restrictions.RestrictionEnum;
import com.ssomar.score.usedapi.AllWorldManager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GeneralConfig extends Config {

    private static GeneralConfig instance;

    private List<String> disableWorlds;
    private boolean premiumEnableCooldown;
    private boolean checkVersionMsg;
    private boolean disableBackup;
    private int deleteBackupsAfterDays;

    public GeneralConfig() {
        super("config.yml");
        super.setup(ExecutableEvents.plugin);
    }

    public static GeneralConfig getInstance() {
        if (instance == null) instance = new GeneralConfig();
        return instance;
    }

    @Override
    public void load() {

        /* Disableworld config */
        disableWorlds = new ArrayList<>();
        for (String str : config.getStringList("disable-world")) {
            if (AllWorldManager.getWorld(str).isPresent()) {
                disableWorlds.add(str);
            } else
                ExecutableEvents.plugin.getServer().getLogger().severe(ExecutableEvents.plugin.getNameDesign()+" Invalid world: " + str + " in the option disable-world");
        }

        checkVersionMsg = config.getBoolean("checkVersionMsg", true);

        /* premiumEnableCooldown */
        premiumEnableCooldown = config.getBoolean("premium-enable-cooldown-for-op", false);

        disableBackup = config.getBoolean("disableBackup", false);

        deleteBackupsAfterDays = config.getInt("deleteBackupsAfterDays", 7);
        if (deleteBackupsAfterDays < 0) deleteBackupsAfterDays = 14;
    }

    public void reload() {
        instance = new GeneralConfig();
    }

}
