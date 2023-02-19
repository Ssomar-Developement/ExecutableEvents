package com.ssomar.executableevents.commands;


import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.configs.api.PlaceholderAPI;
import com.ssomar.executableevents.editor.ExecutableEventsEditor;
import com.ssomar.executableevents.events.optimize.OptimizedEventsHandler;
import com.ssomar.executableevents.executableevents.ExecutableEvent;
import com.ssomar.executableevents.executableevents.ExecutableEventLoader;
import com.ssomar.executableevents.executableevents.manager.ExecutableEventsManager;
import com.ssomar.score.SCore;
import com.ssomar.score.actionbar.ActionbarCommands;
import com.ssomar.score.commands.score.clear.ClearCommand;
import com.ssomar.score.commands.score.clear.ClearType;
import com.ssomar.score.sobject.menu.NewSObjectsManagerEditor;
import com.ssomar.score.utils.StringConverter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CommandsClass implements CommandExecutor, TabExecutor {

    private static List<String> argumentsQuantity = new ArrayList<>();
    private static List<String> argumentsUsage = new ArrayList<>();
    private ExecutableEvents main;

    public CommandsClass(ExecutableEvents main) {
        this.main = main;

        argumentsQuantity.add("1");
        argumentsQuantity.add("3");
        argumentsQuantity.add("5");
        argumentsQuantity.add("10");
        argumentsQuantity.add("25");

        argumentsUsage.add("5");
        argumentsUsage.add("10");
        argumentsUsage.add("20");
        argumentsUsage.add("50");
        argumentsUsage.add("100");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0) {

            switch (args[0]) {
                case "test":
                    ExecutableEventsManager.getInstance().getLoadedObjects().get(0).openEditor((Player) sender);
                    break;
                case "reload":
                    this.runCommand(sender, "reload", args);
                    break;
                case "create":
                    this.runCommand(sender, "create", args);
                    break;
                case "show":
                    this.runCommand(sender, "show", args);
                    break;
                case "editor":
                    this.runCommand(sender, "editor", args);
                    break;
                case "edit":
                    this.runCommand(sender, "edit", args);
                    break;
                case "clear":
                    this.runCommand(sender, "clear", args);
                    break;
                case "actionbar":
                    this.runCommand(sender, "actionbar", args);
                    break;
                case "delete":
                    this.runCommand(sender, "delete", args);
                    break;
                case "checkevents":
                    this.runCommand(sender, "checkevents", args);
                    break;
                case "default_events":
                    this.runCommand(sender, "default_events", args);
                    break;
                case "debug":
                    this.runCommand(sender, "debug", args);
                    break;
                default:
                    sender.sendMessage(StringConverter.coloredString("&4" + ExecutableEvents.getPluginSt().getNameDesign() + " &cInvalid argument /ee [ reload | show | edit | editor | actionbar | create | clear ]"));
                    break;
            }
        } else {
            sender.sendMessage(StringConverter.coloredString("&4" + ExecutableEvents.getPluginSt().getNameDesign() + " &cInvalid argument /ee [ reload | show | edit | editor | actionbar | create | clear | delete ]"));
        }
        return true;
    }

    public void runCommand(CommandSender sender, String command, String[] fullArgs) {

        String[] args;
        String typedCommand = "";
        if (fullArgs.length > 1) {
            args = new String[fullArgs.length - 1];
            for (int i = 0; i < fullArgs.length; i++) {
                typedCommand += fullArgs[i] + " ";
                if (i == 0) continue;
                else args[i - 1] = fullArgs[i];
            }
        } else args = new String[0];
        Player player = null;
        if ((sender instanceof Player)) {
            player = (Player) sender;
            if (!(player.hasPermission("ee.cmd." + command) || player.hasPermission("ee.cmds") || player.hasPermission("ee.*"))) {
                player.sendMessage(StringConverter.coloredString("&4" + ExecutableEvents.getPluginSt().getNameDesign() + " &cYou don't have the permission to execute this command: " + "&6ee.cmd." + command));
                return;
            }
        }

        switch (command) {

            case "reload":
                main.onReload(true);
                if (!PlaceholderAPI.isLotOfWork())
                    sender.sendMessage(ChatColor.GREEN + "ExecutableEvents has been reload !");
                else sender.sendMessage(ChatColor.GREEN + "ExecutableEvents has been reload");
                ExecutableEvents.getPluginSt().getLogger().info("[ExecutableEvents] Successfully reload !");
                break;
            case "debug":
                if (player != null) {
                    if (DebugMode.getInstance().getPlayersInDebugMode().contains(player)) {
                        DebugMode.getInstance().getPlayersInDebugMode().remove(player);
                        sender.sendMessage(ChatColor.GOLD + "ExecutableEvents debug mode disabled !");
                    } else {
                        DebugMode.getInstance().getPlayersInDebugMode().add(player);
                        sender.sendMessage(ChatColor.GREEN + "ExecutableEvents debug mode enabled !");
                    }
                } else {
                    if (DebugMode.getInstance().isEnabledInConsole()) {
                        DebugMode.getInstance().setEnabledInConsole(false);
                        sender.sendMessage(ChatColor.GOLD + "ExecutableEvents debug mode disabled !");
                    } else {
                        DebugMode.getInstance().setEnabledInConsole(true);
                        sender.sendMessage(ChatColor.GREEN + "ExecutableEvents debug mode enabled !");
                    }
                }
                break;
            case "checkevents":
                OptimizedEventsHandler.getInstance().displayOptimisation();
                if (player != null) {
                    player.sendMessage(ChatColor.GREEN + "Check events sended in your console !");
                }
                break;
            case "default_events":
                ExecutableEventLoader.getInstance().createDefaultObjectsFile(!PlaceholderAPI.isLotOfWork(), true);
                main.onReload(true);
                if (player != null) {
                    player.sendMessage(ChatColor.GREEN + "ExecutableEvents regenerates the default items, check them in the /ee show !");
                } else {
                    sender.sendMessage(ChatColor.GREEN + "ExecutableEvents regenerates the default items, check them in the /ee show !");
                }
                break;

            case "create":
                if (player != null) {
                    if (args.length == 1) {
                        if (ExecutableEventLoader.getInstance().getAllObjectsLowerCases().contains(args[0])) {
                            player.sendMessage(StringConverter.coloredString("&4[ExecutableEvents] &cError this id already exist re-enter /ee create ID (ID is the id you want for your new event)"));
                        } else {
                            if (ExecutableEventsManager.getInstance().getLoadedObjects().size() >= ExecutableEvents.plugin.getMaxSObjectsLimit() && PlaceholderAPI.isLotOfWork()) {
                                player.sendMessage(StringConverter.coloredString("&4[ExecutableEvents] &cREQUIRE PREMIUM: to add more than "+ExecutableEvents.plugin.getMaxSObjectsLimit()+" events you need the premium version"));
                                return;
                            }
                            //Bukkit.broadcastMessage(player.getInventory().getItemInMainHand()+"");

                            ItemStack item;
                            if (SCore.is1v11Less()) item = player.getItemInHand();
                            else item = player.getInventory().getItemInMainHand();

                            ExecutableEvent i = new ExecutableEvent(args[0], "plugins/ExecutableEvents/events/" + args[0] + ".yml");
                            i.save();
                            ExecutableEventsManager.getInstance().addLoadedObject(i);
                            i.openEditor(player);
                        }
                    } else {
                        player.sendMessage(StringConverter.coloredString("&2[ExecutableEvents] &aTo create a new event you need to enter &e/ee create ID &a(ID is the id you want for your new event)"));
                    }
                }
                break;
            case "show":
            case "editor":
                if (player != null) {
                    NewSObjectsManagerEditor.getInstance().startEditing(player, new ExecutableEventsEditor());
                }
                break;
            case "edit":
                if (player != null) {
                    if (args.length > 0) {
                        try {
                            Optional<ExecutableEvent> ei = ExecutableEventsManager.getInstance().getLoadedObjectWithID(args[0]);
                            if (ei.isPresent()) {
                                ei.get().openEditor(player);
                            } else {
                                player.sendMessage(StringConverter.coloredString("&4[ExecutableEvents] &cThis ExecutableItems " + args[0] + " doesn't exist"));
                            }
                        } catch (NullPointerException e) {
                            sender.sendMessage(StringConverter.coloredString("&4[ExecutableEvents] &cError > Invalid EventID /ee edit {EventID}"));
                        }
                    } else
                        sender.sendMessage(StringConverter.coloredString("&4[ExecutableEvents] &cError > Invalid EventID /ee edit {EventID}"));
                }
                break;

            case "clear":
                ClearCommand.clearCmd(ExecutableEvents.plugin, sender, args);
                break;

            case "actionbar":
                if (sender instanceof Player) {
                    if (args.length == 1) {
                        ActionbarCommands.manageCommand((Player) sender, args[0]);
                    } else
                        sender.sendMessage(StringConverter.coloredString("&4[ExecutableEvents] &c/ee actionbar on or /ee actionbar off"));
                } else
                    sender.sendMessage(StringConverter.coloredString("&4[ExecutableEvents] &cThis is only an Ingame command !"));
                break;
            case "delete":
                if (args.length == 2) {

                    if (!args[1].equalsIgnoreCase("confirm")) {
                        sender.sendMessage(StringConverter.coloredString("&4[ExecutableEvents] &cTo confirm the delete type &6/ee delete {eventID} confirm"));
                        return;
                    }
                    Optional<ExecutableEvent> eiOpt = ExecutableEventsManager.getInstance().getLoadedObjectWithID(args[0]);
                    if (eiOpt.isPresent()) {
                        ExecutableEventsManager.getInstance().deleteObject(args[0]);
                        sender.sendMessage(StringConverter.coloredString("&&2[ExecutableEvents] &aEvent &e" + args[0] + " &adeleted"));
                        if (player != null)
                            NewSObjectsManagerEditor.getInstance().startEditing(player, new ExecutableEventsEditor());
                    } else
                        sender.sendMessage(StringConverter.coloredString("&4[ExecutableEvents] &cEvent &6" + args[0] + " &cnot found"));
                } else
                    sender.sendMessage(StringConverter.coloredString("&4[ExecutableEvents] &cTo confirm the delete type &6/ee delete {eventID} confirm"));
                break;
            case "projectiles":
                sender.sendMessage(StringConverter.coloredString("&2" + ExecutableEvents.getPluginSt().getNameDesign() + " &aTo open the editor of custom projectiles type: &e/score projectiles"));
                sender.sendMessage(StringConverter.coloredString("&2" + ExecutableEvents.getPluginSt().getNameDesign() + " &aTo create a new custom projectile type: &e/score projectiles-create"));
                sender.sendMessage(StringConverter.coloredString("&2" + ExecutableEvents.getPluginSt().getNameDesign() + " &aTo delete a custom projectile type: &e/score projectiles-delete"));
                break;
            default:
                break;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ee")) {
            ArrayList<String> arguments = new ArrayList<String>();
            if (args.length == 1) {

                arguments.add("reload");
                arguments.add("show");
                arguments.add("editor");
                arguments.add("edit");
                arguments.add("actionbar");
                arguments.add("create");
                arguments.add("clear");
                arguments.add("checkevents");
                arguments.add("default_events");
                arguments.add("debug");
                List<String> argumentsPerm = new ArrayList<String>();
                for (String str : arguments) {
                    if (sender.hasPermission("ee.cmd." + command) || sender.hasPermission("ee.cmds") || sender.hasPermission("ee.*")) {
                        argumentsPerm.add(str);
                    }
                }

                Collections.sort(argumentsPerm);
                return argumentsPerm;
            } else if (args.length >= 2) {

                switch (args[0]) {

                    case "clear":
                        if (args.length == 3) {
                            for (ClearType type : ClearType.values()) {
                                arguments.add(type.name());
                            }
                            return arguments;
                        }
                        break;
                    case "edit":
                        if (args.length == 2) {
                            return getLoadedEventsWith(args[1]);
                        }
                        break;
                    case "actionbar":
                        if (args.length == 2) {
                            arguments.add("on");
                            arguments.add("off");

                            return arguments;
                        }
                        break;
                    case "delete":
                        if (args.length == 2) {
                            return getLoadedEventsWith(args[1]);
                        } else if (args.length == 3) {
                            arguments.add("confirm");

                            return arguments;
                        }
                    default:
                        break;
                }
            }
        }
        return null;
    }

    public List<String> getLoadedEventsWith(String str) {
        List<String> arguments = new ArrayList<>();

        if (!str.isEmpty()) {
            for (ExecutableEvent item : ExecutableEventsManager.getInstance().getLoadedObjects()) {
                if (item.getId().toUpperCase().contains(str.toUpperCase()))
                    arguments.add(item.getId());
            }
        } else {
            for (ExecutableEvent item : ExecutableEventsManager.getInstance().getLoadedObjects()) {
                arguments.add(item.getId());
            }
        }

        return arguments;
    }
}

