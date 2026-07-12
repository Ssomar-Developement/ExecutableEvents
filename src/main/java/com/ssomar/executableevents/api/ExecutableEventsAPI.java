package com.ssomar.executableevents.api;


import com.ssomar.executableevents.executableevents.manager.ExecutableEventsManager;

/**
 * @deprecated use the public API in SCore instead:
 * {@link com.ssomar.score.api.executableevents.ExecutableEventsAPI} — it returns
 * interfaces, is available on the public Maven repo (repo.ssomar.com) and does not
 * require compiling against the ExecutableEvents jar.
 */
@Deprecated
public class ExecutableEventsAPI {

    /**
     * Get the ExecutableEvents Manager.
     *
     * @deprecated use {@link com.ssomar.score.api.executableevents.ExecutableEventsAPI#getExecutableEventsManager()}
     **/
    @Deprecated
    public static ExecutableEventsManager getExecutableEventsManager() {
        return ExecutableEventsManager.getInstance();
    }
}
