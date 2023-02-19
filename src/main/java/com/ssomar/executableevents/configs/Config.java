package com.ssomar.executableevents.configs;

import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;

public abstract class Config {
    protected File pdfile;

    protected FileConfiguration config;

    protected String fileName;

    protected Config(String fileName) {
        this.fileName = fileName;
    }

    public void setup(Plugin plugin) {
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();
        this.pdfile = new File(plugin.getDataFolder(), this.fileName);
        if (!this.pdfile.exists()) {
            try {
                this.pdfile.getParentFile().mkdir();
                this.pdfile.createNewFile();
                try (InputStream is = plugin.getResource(this.fileName);
                     OutputStream os = new FileOutputStream(this.pdfile)) {
                    ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to create the file: " + this.fileName, e);
            } catch (NullPointerException e) {/* locale */}
        }
        this.config = (FileConfiguration) YamlConfiguration.loadConfiguration(this.pdfile);
        plugin.reloadConfig();
        load();
    }

    abstract void load();

    public void save() {
        try {
            this.config.save(this.pdfile);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe("Could not save " + this.fileName + "!");
        }
    }


    public FileConfiguration getConfig() {
        return this.config;
    }

    public String getFileName() {
        return this.fileName;
    }
}

