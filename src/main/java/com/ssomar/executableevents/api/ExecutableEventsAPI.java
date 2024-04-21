package com.ssomar.executableevents.api;


import com.ssomar.executableevents.executableevents.manager.ExecutableEventsManager;

public class ExecutableEventsAPI {

    /**
     * Get the ExecutableEvents Manager,
     * It allows you to get / retrieve the ExecutableBlocks Configurations
     **/
    public static ExecutableEventsManager getExecutableEventsManager() {
        return ExecutableEventsManager.getInstance();
    }
}
