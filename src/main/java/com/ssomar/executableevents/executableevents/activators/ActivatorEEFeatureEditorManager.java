package com.ssomar.executableevents.executableevents.activators;


import com.ssomar.score.editor.NewInteractionClickedGUIManager;
import com.ssomar.score.features.FeatureInterface;
import com.ssomar.score.features.editor.FeatureEditorManagerAbstract;
import com.ssomar.score.features.types.SOptionFeature;
import com.ssomar.score.features.types.TypeTargetFeature;

public class ActivatorEEFeatureEditorManager extends FeatureEditorManagerAbstract<ActivatorEEFeatureEditor, ActivatorEEFeature> {

    private static ActivatorEEFeatureEditorManager instance;

    public static ActivatorEEFeatureEditorManager getInstance() {
        if (instance == null) {
            instance = new ActivatorEEFeatureEditorManager();
        }
        return instance;
    }

    @Override
    public ActivatorEEFeatureEditor buildEditor(ActivatorEEFeature parent) {
        return new ActivatorEEFeatureEditor(parent.clone(parent.getParent()));
    }

    public void reloadEditor(NewInteractionClickedGUIManager<ActivatorEEFeatureEditor> i) {
        for (FeatureInterface feature : i.gui.getParent().getFeatures()) {
            if (feature instanceof SOptionFeature || feature instanceof TypeTargetFeature) {
                i.gui.load();
            }
        }
    }


    @Override
    public boolean shiftLeftClicked(NewInteractionClickedGUIManager<ActivatorEEFeatureEditor> i) {
        boolean result = super.shiftLeftClicked(i);
        if (result) {
            reloadEditor(i);
        }
        return result;
    }

    @Override
    public boolean shiftRightClicked(NewInteractionClickedGUIManager<ActivatorEEFeatureEditor> i) {
        boolean result = super.shiftRightClicked(i);
        if (result) {
            reloadEditor(i);
        }
        return result;
    }

    @Override
    public boolean leftClicked(NewInteractionClickedGUIManager<ActivatorEEFeatureEditor> i) {
        boolean result = super.leftClicked(i);
        if (result) {
            reloadEditor(i);
        }
        return result;
    }

    @Override
    public boolean rightClicked(NewInteractionClickedGUIManager<ActivatorEEFeatureEditor> i) {
        boolean result = super.rightClicked(i);
        if (result) {
            reloadEditor(i);
        }
        return result;
    }
}
