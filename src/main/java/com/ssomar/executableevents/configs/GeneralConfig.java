package com.ssomar.executableevents.configs;

import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.score.SsomarDev;
import com.ssomar.score.config.Config;
import com.ssomar.score.features.custom.restrictions.RestrictionEnum;
import com.ssomar.score.usedapi.AllWorldManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GeneralConfig extends Config {

    private static GeneralConfig instance;

    private List<String> disableWorlds;

    public enum Setting {
        premiumEnableCooldownForOp,
        checkVersionMsg,
        disableBackup,
        deleteBackupsAfterDays;
    }

    public GeneralConfig() {
        super("config.yml");
        super.setup(ExecutableEvents.plugin.getPlugin());
    }

    public static GeneralConfig getInstance() {
        if (instance == null) instance = new GeneralConfig();
        return instance;
    }

    @Override
    public boolean converter(FileConfiguration fileConfiguration) {
        if(!fileConfiguration.contains("config_12_04_2024")) {
            fileConfiguration.set("config_12_04_2024", true);

            fileConfiguration.set("premiumEnableCooldownForOp", fileConfiguration.get("premium-enable-cooldown-for-op", true));
            fileConfiguration.set("premium-enable-cooldown-for-op", null);

            fileConfiguration.set("disabledWorlds", fileConfiguration.get("disable-world", new ArrayList<String>()));
            fileConfiguration.set("disable-world", null);

            fileConfiguration.set("pickupLimit", fileConfiguration.get("pickup-limit", -1));
            fileConfiguration.set("pickup-limit", null);

            return true;
        }
        return false;
    }

    @Override
    public void load() {

        /* Disableworld config */
        disableWorlds = new ArrayList<>();
        for (String str : config.getStringList("disabledWorlds")) {
            if (AllWorldManager.getWorld(str).isPresent()) {
                disableWorlds.add(str);
            } else
                ExecutableEvents.plugin.getPlugin().getServer().getLogger().severe(ExecutableEvents.plugin.getNameDesign()+" Invalid world: " + str + " in the option disabledWorlds");
        }

        loadBooleanSetting(Setting.premiumEnableCooldownForOp.name(),  false);

        SsomarDev.testMsg("premiumEnableCooldownForOp: "+getBooleanSetting(Setting.premiumEnableCooldownForOp.name()), true);

        loadBooleanSetting(Setting.checkVersionMsg.name(), true);

        loadBooleanSetting(Setting.disableBackup.name(), false);

        if (loadIntSetting(Setting.deleteBackupsAfterDays.name(), 7) < 0) getLoadedSettings().put(Setting.deleteBackupsAfterDays.name(), 14);
    }

    public void reload() {
        instance = new GeneralConfig();
    }

}
