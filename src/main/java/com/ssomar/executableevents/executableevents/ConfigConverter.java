package com.ssomar.executableevents.executableevents;

import com.google.common.base.Charsets;
import com.ssomar.executableevents.ExecutableEvents;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfigConverter {


    public static void updateTo(File file) {

        FileConfiguration config;
        try {
            config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        if (!config.contains("config_1_98")) {
            config.set("config_1_98", true);

            if (config.isConfigurationSection("activators")) {
                ConfigurationSection activatorsSection = config.getConfigurationSection("activators");
                for (String activatorID : activatorsSection.getKeys(false)) {

                    ConfigurationSection activatorSection = activatorsSection.getConfigurationSection(activatorID);
                    if (activatorSection == null) continue;

                    List<String> commandsType = new ArrayList<>();
                    commandsType.add("commands");
                    commandsType.add("targetPlayerCommands");
                    commandsType.add("targetEntityCommands");
                    commandsType.add("entityCommands");
                    commandsType.add("blockCommands");
                    commandsType.add("targetBlockCommands");

                    for (String type : commandsType) {
                        if (activatorSection.contains(type)) {
                            List<String> commands = activatorSection.getStringList(type);
                            List<String> newCommands = new ArrayList<>();
                            for (String command : commands) {
                                if (command.contains("AROUND") && command.contains("CONDITIONS(")) {
                                    String[] split = command.split("CONDITIONS\\(");
                                    String[] split2 = split[1].split("\\)");
                                    command = split[0] + "CONDITIONS(" + split2[0].replace("&", "&&") + ")" + split2[1];
                                }
                                newCommands.add(command);
                            }
                            activatorSection.set(type, newCommands);
                        }
                    }
                }
            }
        }

        config.set("config_update", !ExecutableEvents.plugin.isLotOfWork());

        try {
            Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);

            try {
                writer.write(config.saveToString());
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
