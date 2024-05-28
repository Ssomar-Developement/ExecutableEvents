package com.ssomar.executableevents.executableevents.manager;

import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.events.optimize.OptimizedEventsHandler;
import com.ssomar.executableevents.executableevents.ExecutableEvent;
import com.ssomar.executableevents.executableevents.ExecutableEventLoader;
import com.ssomar.executableevents.executableevents.activators.ActivatorEEFeature;
import com.ssomar.score.features.custom.activators.activator.SActivator;
import com.ssomar.score.sobject.SObjectManager;
import com.ssomar.score.sobject.SObjectWithFileManager;
import com.ssomar.score.sobject.sactivator.SOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ExecutableEventsManager extends SObjectWithFileManager<ExecutableEvent> {

    private static ExecutableEventsManager instance;

    public ExecutableEventsManager() {
        super(ExecutableEvents.plugin);
    }

    public static ExecutableEventsManager getInstance() {
        if (instance == null) {
            instance = new ExecutableEventsManager();
        }
        return instance;
    }

    @Override
    public void actionOnObjectWhenLoading(ExecutableEvent newExecutableItem) {

        for (SActivator activator : newExecutableItem.getActivators().getActivators().values()) {

            /* Register only the activators from EI*/
            SOption option = activator.getOption();
            if (activator instanceof ActivatorEEFeature) {
                ActivatorEEFeature activatorEE = (ActivatorEEFeature) activator;
                OptimizedEventsHandler.getInstance().read(option);
            }
        }
    }

    @Override
    public void actionOnObjectWhenReloading(ExecutableEvent newExecutableItem) {
    }

    @Override
    public Optional<ExecutableEvent> methodObjectLoading(String id) {
        return ExecutableEventLoader.getInstance().getObjectById(id, false);
    }


    public boolean isValidID(String id) {
        for (ExecutableEvent item : this.getLoadedObjects()) {
            if (item.getId().equals(id)) return true;
        }
        return false;
    }

    public Optional<ExecutableEvent> getExecutableEvent(String id) {
        for (ExecutableEvent item : this.getLoadedObjects()) {
            if (item.getId().equals(id)) return Optional.of(item);
        }
        return Optional.empty();
    }

    public List<String> getFoldersNames() {
        List<String> folders = new ArrayList<>();
        for (ExecutableEvent item : this.getLoadedObjects()) {
            String path = item.getPath();
            path = path.replace("\\", "/");
            path = path.replace("plugins/ExecutableEvents/events/", "");
            if (path.contains("/")) {
                String ymlPart = "";
                String[] parts = path.split("/");
                ymlPart = parts[parts.length - 1];
                path = path.replace("/" + ymlPart, "");
            } else continue;
            if (!folders.contains(path)) folders.add(path);
        }
        return folders;
    }

    public List<ExecutableEvent> getExecutableEventsOfFolder(String folder) {
        List<ExecutableEvent> executableItems = new ArrayList<>();
        for (ExecutableEvent item : this.getLoadedObjects()) {
            String path = item.getPath();
            path = path.replace("\\", "/");
            path = path.replace("plugins/ExecutableEvents/events/", "");
            if (path.contains("/")) {
                String ymlPart = "";
                String[] parts = path.split("/");
                ymlPart = parts[parts.length - 1];
                path = path.replace("/" + ymlPart, "");
            } else continue;
            if (path.equalsIgnoreCase(folder)) executableItems.add(item);
        }
        return executableItems;
    }

    public List<String> getExecutableEventIdsList() {
        List<String> list = new ArrayList<>();
        for (ExecutableEvent item : this.getLoadedObjects()) {
            list.add(item.getId());
        }
        return list;
    }


}
