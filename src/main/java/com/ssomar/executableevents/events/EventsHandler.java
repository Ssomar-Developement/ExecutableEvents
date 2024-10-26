package com.ssomar.executableevents.events;

import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.editor.EditorInteractionsListener;
import com.ssomar.executableevents.events.player.PlayerEvt;


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

        /** Recode part **/
        main.getServer().getPluginManager().registerEvents(new EditorInteractionsListener(), main);

        main.getServer().getPluginManager().registerEvents(new PlayerEvt(), main);

    }
}
