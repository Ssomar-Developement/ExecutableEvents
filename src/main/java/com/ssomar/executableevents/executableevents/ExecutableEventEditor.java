package com.ssomar.executableevents.executableevents;

import com.ssomar.score.config.GeneralConfig;
import com.ssomar.score.features.FeatureSettingsSCore;
import com.ssomar.score.features.editor.FeatureEditorInterface;
import com.ssomar.score.languages.messages.TM;
import com.ssomar.score.languages.messages.Text;
import com.ssomar.score.menu.GUI;

public class ExecutableEventEditor extends FeatureEditorInterface<ExecutableEvent> {

    private ExecutableEvent newExecutableEvent;

    public ExecutableEventEditor(ExecutableEvent newExecutableItem) {
        super(FeatureSettingsSCore.EXECUTABLEEVENT, 6 * 9);
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
        createItem(ORANGE, 1, 46, GUI.RESET, false, false, TM.gA(Text.EDITOR_RESET_DESCRIPTION));
        // exit
        createItem(RED, 1, 45, GUI.BACK, false, false);

        // change lang menu
        createItem(YELLOW, 1, 47, GUI.CHANGE_LANGUAGE, false, false, GeneralConfig.getInstance().getAvailableLocales("", "&e&oClick here to change the language"));


        //Save menu
        createItem(GREEN, 1, 53, GUI.SAVE, false, false, TM.gA(Text.EDITOR_SAVE_DESCRIPTION));

    }

    @Override
    public ExecutableEvent getParent() {
        return newExecutableEvent;
    }
}
