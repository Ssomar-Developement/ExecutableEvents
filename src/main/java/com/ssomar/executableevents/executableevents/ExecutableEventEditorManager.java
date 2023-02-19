package com.ssomar.executableevents.executableevents;

import com.ssomar.score.editor.NewInteractionClickedGUIManager;
import com.ssomar.score.features.editor.FeatureEditorManagerAbstract;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ExecutableEventEditorManager extends FeatureEditorManagerAbstract<ExecutableEventEditor, ExecutableEvent> {

    private static ExecutableEventEditorManager instance;

    public static ExecutableEventEditorManager getInstance() {
        if (instance == null) {
            instance = new ExecutableEventEditorManager();
        }
        return instance;
    }

    @Override
    public ExecutableEventEditor buildEditor(ExecutableEvent featureParentInterface) {
        return new ExecutableEventEditor(featureParentInterface.clone(featureParentInterface.getParent()));
    }

    public void reloadEditor(NewInteractionClickedGUIManager<ExecutableEventEditor> i) {
        i.gui.load();
        i.player.updateInventory();
    }


    @Override
    public void receiveMessage(NewInteractionClickedGUIManager<ExecutableEventEditor> i) {
        super.receiveMessage(i);
        reloadEditor(i);
    }

    @Override
    public void clicked(ItemStack item, NewInteractionClickedGUIManager<ExecutableEventEditor> interact, ClickType click) {
        super.clicked(item, interact, click);
        reloadEditor(interact);
    }

}
