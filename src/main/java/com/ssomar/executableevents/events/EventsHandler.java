package com.ssomar.executableevents.events;

import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.editor.EditorInteractionsListener;
import com.ssomar.executableevents.events.block.BlockEvt;
import com.ssomar.executableevents.events.entity.EntityEvt;


public class EventsHandler {

    private static EventsHandler instance;

    private ExecutableEvents main;

    public static EventsHandler getInstance() {
        if (instance == null) instance = new EventsHandler();
        return instance;
    }

    public void setup(ExecutableEvents main) {
        this.main = main;
        setupEvents();
    }

    public void setupEvents() {

        main.getServer().getPluginManager().registerEvents(new BlockEvt(), main);

        main.getServer().getPluginManager().registerEvents(new EntityEvt(), main);

        /** Recode part **/
        main.getServer().getPluginManager().registerEvents(new EditorInteractionsListener(), main);

    }
}
