package com.ssomar.executableevents.events.player;

import com.ssomar.executableevents.ExecutableEvents;
import com.ssomar.executableevents.configs.GeneralConfig;
import com.ssomar.score.SCore;
import com.ssomar.score.utils.CheckVersionSpigot;
import com.ssomar.score.utils.strings.StringConverter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvt implements Listener {


    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if (p.isOp() && GeneralConfig.getInstance().getBooleanSetting(GeneralConfig.Setting.checkVersionMsg.name())) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {

                    try {
                        String cVer = CheckVersionSpigot.getVersionOf("107622");
                        if (cVer == null) return;
                        String aVer = ExecutableEvents.getPluginSt().getDescription().getVersion();

                        if (!cVer.equals(aVer)) {
                            p.sendMessage(StringConverter.coloredString("&8&l&oExecutableEvents &8(&cOnly for op&8)  &7Your version of ExecutableEvents is out of date. &8(&7Current: &6" + aVer + "&8) &8(&7Latest: &a" + cVer + "&8) &7Update your plugin for the latest features / bug fixes / better performance !"));
                        }
                    } catch (Exception err) {
                        err.printStackTrace();
                    }

                }
            };
            SCore.schedulerHook.runAsyncTask(runnable, 0L);
        }
    }

}
