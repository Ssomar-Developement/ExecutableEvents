package com.ssomar.executableevents.executableevents.activators;

import com.ssomar.score.features.editor.FeatureEditorInterface;
import com.ssomar.score.menu.GUI;
import com.ssomar.score.utils.emums.TypeTarget;

public class ActivatorEEFeatureEditor extends FeatureEditorInterface<ActivatorEEFeature> {

    public ActivatorEEFeature activator;

    public ActivatorEEFeatureEditor(ActivatorEEFeature activator) {
        super("&lActivator EE feature Editor", 6 * 9);
        this.activator = activator.clone(activator.getParent());
        load();
    }

    @Override
    public void load() {
        clearAndSetBackground();

        activator.getDisplayName().initAndUpdateItemParentEditor(this, 0);
        activator.getOptionFeature().initAndUpdateItemParentEditor(this, 1);

        activator.getCooldown().initAndUpdateItemParentEditor(this, 2);
        activator.getUsePerDayFeature().initAndUpdateItemParentEditor(this, 4);

        if (activator.getOption().isLoopOption())
            activator.getLoopFeatures().initAndUpdateItemParentEditor(this, 5);

        if (Option.getOptionWithDrops().contains(activator.getOption()))
            activator.getDesactiveDrops().initAndUpdateItemParentEditor(this, 5);

        if (Option.getOptionWithDetailedDamageCauses().contains(activator.getOption())) {
            activator.getDetailedDamageCauses().initAndUpdateItemParentEditor(this, 14);
        } else if (Option.getOptionWithCommand().contains(activator.getOption())) {
            activator.getDetailedCommands().initAndUpdateItemParentEditor(this, 14);
        } else if (Option.getOptionWithDetailedItems().contains(activator.getOption())) {
            activator.getDetailedItems().initAndUpdateItemParentEditor(this, 14);
        }

        if (Option.getOptionWithPlayerSt().contains(activator.getOption())) {
            activator.getPlayerConditions().initAndUpdateItemParentEditor(this, 7);
            activator.getPlayerCommands().initAndUpdateItemParentEditor(this, 8);
        } else if (Option.getOptionWithEntitySt().contains(activator.getOption())) {
            activator.getEntityConditions().initAndUpdateItemParentEditor(this, 7);
            activator.getEntityCommands().initAndUpdateItemParentEditor(this, 8);
            activator.getDetailedEntities().initAndUpdateItemParentEditor(this, 6);
        } else if (Option.getOptionWithBlockSt().contains(activator.getOption())) {
            activator.getBlockConditions().initAndUpdateItemParentEditor(this, 7);
            activator.getBlockCommands().initAndUpdateItemParentEditor(this, 8);
            activator.getDetailedBlocks().initAndUpdateItemParentEditor(this, 6);
        }


        if (Option.getOptionWithOnlyTypeClick().contains(activator.getOption())) {
            activator.getTypeTargetFeature().initAndUpdateItemParentEditor(this, 10);
        } else if (Option.getOptionWithDetailedClick().contains(activator.getOption())) {
            activator.getDetailedClickFeature().initAndUpdateItemParentEditor(this, 10);
        }

        activator.getGlobalCooldown().initAndUpdateItemParentEditor(this, 11);

        if (Option.getOptionWithTargetEntitySt().contains(activator.getOption())) {
            activator.getTargetEntityCommands().initAndUpdateItemParentEditor(this, 17);
            activator.getTargetEntityConditions().initAndUpdateItemParentEditor(this, 16);
            activator.getDetailedTargetEntities().initAndUpdateItemParentEditor(this, 15);
        } else if (Option.getOptionWithTargetPlayerSt().contains(activator.getOption())) {
            activator.getTargetPlayerCommands().initAndUpdateItemParentEditor(this, 17);
            activator.getTargetPlayerConditions().initAndUpdateItemParentEditor(this, 16);
        } else if (
                (!Option.getOptionWithOnlyTypeClick().contains(activator.getOption()) && Option.getOptionWithTargetBlockSt().contains(activator.getOption())) ||
                        (Option.getOptionWithOnlyTypeClick().contains(activator.getOption())) && activator.getTypeTargetFeature().getValue().get().equals(TypeTarget.ONLY_BLOCK)
        ) {
            activator.getTargetBlockCommands().initAndUpdateItemParentEditor(this, 17);
            activator.getTargetBlockConditions().initAndUpdateItemParentEditor(this, 16);
            activator.getDetailedTargetBlocks().initAndUpdateItemParentEditor(this, 15);
        }

        activator.getRequiredGroup().initAndUpdateItemParentEditor(this, 21);

        activator.getWorldConditions().initAndUpdateItemParentEditor(this, 25);

        activator.getCancelEvent().initAndUpdateItemParentEditor(this, 27);

        //activator.getItemConditions().initAndUpdateItemParentEditor(this, 34);
        activator.getSilenceOutput().initAndUpdateItemParentEditor(this, 35);

        activator.getPlaceholderConditions().initAndUpdateItemParentEditor(this, 52);

        // Back
        createItem(RED, 1, 45, GUI.BACK, false, false);

        // Reset menu
        createItem(ORANGE, 1, 46, GUI.RESET, false, false, "", "&c&oClick here to reset");

        // Save menu
        createItem(GREEN, 1, 53, GUI.SAVE, false, false, "", "&a&oClick here to save");
    }

    @Override
    public ActivatorEEFeature getParent() {
        return activator;
    }
}
