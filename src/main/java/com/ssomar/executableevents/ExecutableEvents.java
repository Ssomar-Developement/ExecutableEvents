package com.ssomar.executableevents;


import com.alessiodp.libby.BukkitLibraryManager;
import com.alessiodp.libby.Library;
import com.ssomar.executableevents.api.load.ExecutableEventsPostLoadEvent;
import com.ssomar.executableevents.commands.CommandsClass;
import com.ssomar.executableevents.configs.GeneralConfig;
import com.ssomar.executableevents.configs.Message;
import com.ssomar.executableevents.events.EventsHandler;
import com.ssomar.executableevents.events.optimize.OptimizedEventsHandler;
import com.ssomar.executableevents.executableevents.ExecutableEventLoader;
import com.ssomar.score.SCore;
import com.ssomar.score.configs.messages.MessageInterface;
import com.ssomar.score.configs.messages.MessageMain;
import com.ssomar.score.utils.logging.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class ExecutableEvents extends JavaPlugin {

    public static final String NAME = "ExecutableEvents";
    public static final String NAME_COLOR = "&dExecutableEvents";

    public static SExecutableEvents plugin;
    private CommandsClass commandClass;

    public static SExecutableEvents getPluginSt() {
        return plugin;
    }

    @Override
    public void onLoad() {
        /* BukkitLibraryManager bukkitLibraryManager = new BukkitLibraryManager(this);
        bukkitLibraryManager.addRepository("https://jitpack.io");

        Library lib = Library.builder()
                .groupId("com{}github{}Ssomar-Developement") // "{}" is replaced with ".", useful to avoid unwanted changes made by maven-shade-plugin
                .artifactId("SCore")
                .version("main-39c85fe778-1")
                //.relocate("com{}github{}retrooper", "com{}ssomar{}myfurniture{}com{}github{}retrooper")
                //.isolatedLoad(true)
                .resolveTransitiveDependencies(true)
                .build();

        bukkitLibraryManager.loadLibrary(lib);*/
    }


    @Override
    public void onEnable() {
        plugin = new SExecutableEvents(this);
        sendPluginName();

        commandClass = new CommandsClass(plugin);
        plugin.getPlugin().saveDefaultConfig();

        if (plugin.isLotOfWork() && SCore.is1v11Less()) {
            ExecutableEvents.plugin.getPlugin().getServer().getLogger().severe(ExecutableEvents.plugin.getNameDesign() + " ExecutableEvents for 1.11 and less is only for the premium version !");
            sendPluginName();
            SCore.plugin.getServer().getPluginManager().disablePlugin(ExecutableEvents.plugin.getPlugin());
            return;
        }

        /* SCore check part */
        Plugin sCore;
        if ((sCore = Bukkit.getPluginManager().getPlugin("SCore")) != null) {
            /* boolean validVersion = false;
            List<String> validVersions = new ArrayList<>();
            validVersions.add("42.42.42");
            try {
                String version = sCore.getDescription().getVersion();
                validVersion = validVersions.contains(version);

            } catch (Exception e) {
            }

            if (!validVersion) {
                ExecutableEvents.plugin.getServer().getLogger().severe(ExecutableEvents.plugin.getNameDesign()+" To make this version of EE work you need to update SCore !");
                ExecutableEvents.plugin.getServer().getLogger().severe(ExecutableEvents.plugin.getNameDesign()+" Valid versions of SCore: ");
                for (String s : validVersions) {
                    ExecutableEvents.plugin.getServer().getLogger().severe(ExecutableEvents.plugin.getNameDesign()+" Version: " + s);
                }
                ExecutableEvents.plugin.getServer().getLogger().severe(ExecutableEvents.plugin.getNameDesign()+" SCore link: GAVE IN THE .ZIP WHEN YOU DOWNLOAD EE");
                ExecutableEvents.plugin.getServer().getLogger().info("================ "+ExecutableEvents.plugin.getNameDesign()+" ================");
                getServer().getPluginManager().disablePlugin(this);
                return;
            }*/
        }

        /* Events instance part */
        EventsHandler.getInstance().setup(this);

        /* Load of the general config */
        GeneralConfig.getInstance();

        /* Delete old backups */
        File backupfolder;
        if ((backupfolder = new File(plugin.getPlugin().getDataFolder() + "/backups")).exists()) {
            if (backupfolder.isDirectory()) {
                for (File f : backupfolder.listFiles()) {
                    try {
                        String name = f.getName();
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
                        LocalDateTime now = LocalDateTime.now();
                        if (LocalDateTime.from(dtf.parse(name)).plusDays(GeneralConfig.getInstance().getIntSetting(GeneralConfig.Setting.deleteBackupsAfterDays.name(), 30)).isBefore(now)) {
                            //System.out.println("DELETE BACKUP: " + f.getName());
                            for (File f2 : f.listFiles()) {
                                f2.delete();
                            }
                            f.delete();
                        }
                        //else System.out.println("NOT DELETE BACKUP: " + f.getName());
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        }

        /* Items parts */
        /* To create backup at each restart / reload but not at /ei reload */
        ExecutableEventLoader.getInstance().setCreateBackup(true);
        ExecutableEventLoader.getInstance().load();

        MessageMain.getInstance().loadMessagesOf(plugin.getPlugin(), MessageInterface.getMessagesEnum(Message.values()));

        /* Commands part */
        this.getCommand("ee").setExecutor(commandClass);

        /* BStats part */
        int pluginId = 16889;
        @SuppressWarnings("unused")
        MetricsLite metrics = new MetricsLite(this, pluginId);

        sendPluginName();

        Bukkit.getPluginManager().callEvent(new ExecutableEventsPostLoadEvent());
    }

    public void onReload(boolean PluginCommand) {
        sendPluginName();
        plugin.getPlugin().saveDefaultConfig();

        OptimizedEventsHandler.getInstance().reload();

        /* Items instance part */
        ExecutableEventLoader.getInstance().reload();

        GeneralConfig.getInstance().reload();

        /* Message instance part */
        MessageMain.getInstance().loadMessagesOf(plugin.getPlugin(), MessageInterface.getMessagesEnum(Message.values()));

        if (PluginCommand) {
        }

        //OptimizedEventsVerification.getInstance().displayOptimisation();

        sendPluginName();
    }

    public void sendPluginName() {
        if (plugin.isLotOfWork()) Utils.sendConsoleMsg(plugin, "&7================ " + NAME_COLOR + " &7================");
        else Utils.sendConsoleMsg(plugin, "&7========&e*&7======== " + NAME_COLOR + " &7========&e*&7========");
    }

    @Override
    public void onDisable() {
        try {
            if (SCore.plugin.isEnabled()) SCore.plugin.getServer().getPluginManager().disablePlugin(SCore.plugin);
        } catch (Error ignored) {
            //Probably already disabled
            //System.out.println("SCore not found");
        }
    }


}
