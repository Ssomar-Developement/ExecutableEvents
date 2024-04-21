package com.ssomar.executableevents.editor;

import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.executableevents.ExecutableEvent;
import com.ssomar.executableevents.executableevents.ExecutableEventLoader;
import com.ssomar.executableevents.executableevents.manager.ExecutableEventsManager;
import com.ssomar.score.sobject.menu.SObjectsWithFileEditor;

public class ExecutableEventsEditor extends SObjectsWithFileEditor<ExecutableEvent> {


    public ExecutableEventsEditor() {
        super(ExecutableEvents.plugin, "&lExecutableEvents", "/events", ExecutableEventsManager.getInstance(), ExecutableEventLoader.getInstance());
    }

    @Override
    public void initSettings() {
        setGiveButton(false);
    }
}
