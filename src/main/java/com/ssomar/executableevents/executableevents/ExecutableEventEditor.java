package com.ssomar.executableevents.executableevents;

import com.ssomar.score.features.editor.FeatureEditorInterface;
import com.ssomar.score.menu.GUI;

public class ExecutableEventEditor extends FeatureEditorInterface<ExecutableEvent> {

    private ExecutableEvent newExecutableEvent;

    public ExecutableEventEditor(ExecutableEvent newExecutableItem) {
        super("&lExecutable Event Editor", 6 * 9);
        this.newExecutableEvent = newExecutableItem;
        load();
    }

    @Override
    public void load() {
        clearAndSetBackground();
        int i = 0;
        newExecutableEvent.getEnabled().initAndUpdateItemParentEditor(this, i);
        i++;
        newExecutableEvent.getEditorIcon().initAndUpdateItemParentEditor(this, i);
        i++;
        newExecutableEvent.getDisplayName().initAndUpdateItemParentEditor(this, i);
        i++;
        newExecutableEvent.getActivatorsFeature().initAndUpdateItemParentEditor(this, i);
        i++;
        newExecutableEvent.getDisabledWorlds().initAndUpdateItemParentEditor(this, i);


        //Reset menu
        createItem(ORANGE, 1, 46, GUI.RESET, false, false, "", "&c&oClick here to reset", "&c&oall options of this event");
        // exit
        createItem(RED, 1, 45, GUI.BACK, false, false);

        //Save menu
        createItem(GREEN, 1, 53, GUI.SAVE, false, false, "", "&a&oClick here to save", "&a&oyour modification in config.yml");

    }

    @Override
    public ExecutableEvent getParent() {
        return newExecutableEvent;
    }
}
