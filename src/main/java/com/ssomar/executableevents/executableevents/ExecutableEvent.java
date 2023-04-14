package com.ssomar.executableevents.executableevents;

import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.configs.Message;
import com.ssomar.executableevents.configs.api.PlaceholderAPI;
import com.ssomar.executableevents.editor.ExecutableEventsEditor;
import com.ssomar.executableevents.executableevents.activators.ActivatorEEFeature;
import com.ssomar.score.api.executableitems.config.ExecutableItemInterface;
import com.ssomar.score.configs.messages.MessageMain;
import com.ssomar.score.features.FeatureInterface;
import com.ssomar.score.features.FeatureParentInterface;
import com.ssomar.score.features.custom.activators.activator.NewSActivator;
import com.ssomar.score.features.custom.activators.group.ActivatorsFeature;
import com.ssomar.score.features.types.BooleanFeature;
import com.ssomar.score.features.types.ColoredStringFeature;
import com.ssomar.score.features.types.MaterialFeature;
import com.ssomar.score.features.types.list.ListWorldFeature;
import com.ssomar.score.menu.GUI;
import com.ssomar.score.sobject.HigherFormSObject;
import com.ssomar.score.sobject.NewSObject;
import com.ssomar.score.sobject.menu.NewSObjectsManagerEditor;
import com.ssomar.score.splugin.SPlugin;
import com.ssomar.score.utils.FixedMaterial;
import com.ssomar.score.utils.messages.SendMessage;
import com.ssomar.score.utils.strings.StringConverter;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Getter
@Setter
public class ExecutableEvent extends NewSObject<ExecutableEvent, ExecutableEventEditor, ExecutableEventEditorManager> implements ExecutableItemInterface, HigherFormSObject {

    /**
     * Features
     **/
    BooleanFeature enabled;
    ColoredStringFeature displayName;
    private String id;
    private String path;
    private MaterialFeature editorIcon;
    /**
     * Activators / triggers
     **/
    private ActivatorsFeature activatorsFeature;

    private ListWorldFeature disabledWorlds;


    /**
     * For the clone method, the parent is the real instance
     **/
    public ExecutableEvent(FeatureParentInterface parent, String id, String path) {
        super(parent, "EE", "EE", new String[]{}, Material.EMERALD);
        this.id = id;
        this.path = path;
        reset();
    }

    public ExecutableEvent(String id, String path) {
        super("EE", "EE", new String[]{}, Material.EMERALD);
        this.id = id;
        this.path = path;
        reset();
    }

    @Override
    public boolean isPremium() {
        return !ExecutableEvents.plugin.isLotOfWork();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String s) {
        this.id = s;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        description.add("§7ID: §f" + id);
        description.add("§7Enabled: §f" + enabled.getValue());
        ;
        description.add("§7Path: §f" + path);
        description.add("§7Activators: ");
        for (NewSActivator activator : activatorsFeature.getActivators().values()) {
            description.add("§7- " + activator.getId());
        }
        return description;
    }

    @Override
    public List<FeatureInterface> getFeatures() {
        List<FeatureInterface> features = new ArrayList<FeatureInterface>(Arrays.asList(enabled, editorIcon, displayName, disabledWorlds, activatorsFeature));

        return features;
    }

    @Override
    public String getParentInfo() {
        return "(Event: " + id + ")";
    }

    @Override
    public ConfigurationSection getConfigurationSection() {
        File file = getFile();

        FileConfiguration config = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
        return config;
    }

    @Override
    public File getFile() {
        File file = new File(path);
        if (!file.exists()) {
            try {
                new File(path).createNewFile();
                file = ExecutableEventLoader.getInstance().searchFileOfObject(id);
            } catch (IOException ignored) {
                return null;
            }
        }
        return file;
    }

    @Override
    public void reload() {
        if (getParent() instanceof ExecutableEvent) {
            ExecutableEvent item = (ExecutableEvent) getParent();
            item.setEnabled(enabled);
            item.setEditorIcon(editorIcon);
            item.setActivatorsFeature(activatorsFeature);
            item.setDisplayName(displayName);
            item.setDisabledWorlds(disabledWorlds);
        }
    }

    @Override
    public ExecutableEvent clone(FeatureParentInterface parent) {
        ExecutableEvent clone = new ExecutableEvent(this, id, path);
        clone.setEnabled(enabled.clone(clone));
        clone.setEditorIcon(editorIcon.clone(clone));
        clone.setActivatorsFeature(activatorsFeature.clone(clone));
        clone.setDisplayName(displayName.clone(clone));
        clone.setDisabledWorlds(disabledWorlds.clone(clone));

        return clone;
    }


    @Override
    public void openEditor(@NotNull Player player) {
        ExecutableEventEditorManager.getInstance().startEditing(player, this);
    }

    @Override
    public void openBackEditor(@NotNull Player player) {
        NewSObjectsManagerEditor.getInstance().startEditing(player, new ExecutableEventsEditor());
    }

    @Override
    public List<String> load(SPlugin sPlugin, ConfigurationSection configurationSection, boolean premiumLoading) {
        List<String> errors = new ArrayList<>();
        for (FeatureInterface feature : getFeatures()) {
            errors.addAll(feature.load(sPlugin, configurationSection, premiumLoading));
        }

        return errors;
    }

    @Override
    public void save(ConfigurationSection configurationSection) {
        for (FeatureInterface feature : getFeatures()) {
            feature.save(configurationSection);
        }
    }

    @Override
    public ExecutableEvent getValue() {
        return this;
    }

    @Override
    public ExecutableEvent initItemParentEditor(GUI gui, int i) {
        return null;
    }

    @Override
    public void updateItemParentEditor(GUI gui) {

    }

    @Override
    public void reset() {
        this.enabled = new BooleanFeature(this, "enabled", true, "Enabled", new String[]{"&7&oIf the event is enabled"}, Material.LEVER, false, false);

        this.editorIcon = new MaterialFeature(this, "editorIcon", Optional.of(Material.LEVER), "Icon Editor", new String[]{}, Material.LEVER, false, true);

        this.activatorsFeature = new ActivatorsFeature(this, new ActivatorEEFeature(null, "null"));

        this.displayName = new ColoredStringFeature(this, "name", Optional.of("&eDefault name"), "Custom name", new String[]{"&7&oThe custom name of the event"}, Material.NAME_TAG, false, false);

        this.disabledWorlds = new ListWorldFeature(this, "disabledWorlds", new ArrayList<>(), "Disabled worlds", new String[]{"&7&oDisabled worlds"}, FixedMaterial.getMaterial(Arrays.asList("GRASS_BLOCK", "GRASS")), false, false);
    }

    @Override
    public ItemStack addExecutableItemInfos(ItemStack itemStack, Optional<Player> playerOpt) {
        return this.buildItem(1, playerOpt, Optional.empty(), Optional.ofNullable(itemStack), new HashMap<>());
    }

    @Override
    public boolean hasItemPerm(@NotNull Player player, boolean showError) {
        if (player.isOp()) return true;

        if (PlaceholderAPI.isLotOfWork()) {
            if (!(player.hasPermission("ExecutableItems.item." + id) || player.hasPermission("ei.item." + id) || player.hasPermission("ExecutableItems.item.*") || player.hasPermission("ei.item.*") || player.hasPermission("*"))) {
                if (showError)
                    new SendMessage().sendMessage(player, StringConverter.replaceVariable(MessageMain.getInstance().getMessage(ExecutableEvents.plugin, Message.REQUIRE_PERMISSION), player.getName(), displayName.getValue().get(), "", 0));
                return false;
            }
        } else {
            if (player.hasPermission("*")) return true;
            if (!(player.hasPermission("ExecutableItems.item." + id) || player.hasPermission("ei.item." + id) || player.hasPermission("ExecutableItems.item.*") || player.hasPermission("ei.item.*")) || player.hasPermission("-ei.item." + id)) {
                if (showError)
                    new SendMessage().sendMessage(player, StringConverter.replaceVariable(MessageMain.getInstance().getMessage(ExecutableEvents.plugin, Message.REQUIRE_PERMISSION), player.getName(), displayName.getValue().get(), "", 0));
                return false;
            }
        }

        return true;
    }

    @Override
    public Item dropItem(Location location, int i) {
        return null;
    }

    @Override
    public ItemStack buildItem(int quantity, Optional<Integer> usageOpt, Optional<Player> playerOpt, Map<String, String> variables) {
        return new ItemStack(editorIcon.getValue().get());
    }

    @Override
    public ItemStack buildItem(int quantity, Optional<Integer> usageOpt, Optional<Player> playerOpt) {
        return new ItemStack(editorIcon.getValue().get());
    }

    @Override
    public ItemStack buildItem(int quantity, Optional<Player> playerOpt) {
        return new ItemStack(editorIcon.getValue().get());
    }

    @Override
    public @Nullable NewSActivator getActivator(String s) {
        for (NewSActivator acti : activatorsFeature.getActivators().values()) {
            if (acti.getId().equalsIgnoreCase(id)) {
                return acti;
            }
        }
        return null;
    }

    @Override
    public ActivatorsFeature getActivators() {
        return activatorsFeature;
    }


    public ItemStack buildItem(int quantity, Optional<Player> playerOpt, Optional<Integer> usageOpt, Optional<ItemStack> itemStackOpt, Map<String, String> variables) {
        return new ItemStack(editorIcon.getValue().get());
    }

    @Override
    public boolean hasKeepItemOnDeath() {
        return false;
    }

    @Override
    public void addCooldown(Player player, int i, boolean b) {

    }

    @Override
    public void addCooldown(Player player, int i, boolean b, String s) {

    }

    @Override
    public void addGlobalCooldown(int i, boolean b) {

    }

    @Override
    public void addGlobalCooldown(int i, boolean b, String s) {

    }

    public boolean hasCustomModel() {
        return false;
    }
}
