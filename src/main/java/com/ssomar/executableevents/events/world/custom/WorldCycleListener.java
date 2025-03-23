package com.ssomar.executableevents.events.world.custom;

import com.ssomar.executableevents.events.EventsManager;
import com.ssomar.executableevents.executableevents.activators.Option;
import com.ssomar.score.SCore;
import com.ssomar.score.sobject.sactivator.EventInfo;
import com.ssomar.score.utils.scheduler.ScheduledTask;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class WorldCycleListener implements Listener {
    public static List<ScheduledTask> run = new ArrayList<>();

    public WorldCycleListener() {
        Iterator<ScheduledTask> iterator = run.iterator();
        while (iterator.hasNext()) {
            ScheduledTask task = iterator.next();
            task.cancel();
            iterator.remove();
        }
        for (World w : Bukkit.getWorlds()) {
            if (w == null) continue;
            if (w.getEnvironment() == World.Environment.NETHER || w.getEnvironment() == World.Environment.THE_END) continue;
            final boolean[] isDay = {w.getTime() < 12000};
            Runnable r = () -> {
                long time = w.getTime();
                if (time >= 0 && time < 12000 && !isDay[0]) {
                    isDay[0] = true;
                    onDayStart(w);
                }
                if (time >= 12000 && time < 24000 && isDay[0]) {
                    isDay[0] = false;
                    onNightStart(w);
                }
            };
            ScheduledTask hook = SCore.schedulerHook.runRepeatingTask(r, 0, 20);
            run.add(hook);
        }
    }

    protected void onDayStart(World world) {
        EventInfo eInfo = new EventInfo(new FakeDayEvent());
        eInfo.setWorld(Optional.of(world));
        eInfo.getPlaceholders().put("%world%", world.getName());
        eInfo.setOption(Option.WORLD_DAY);
        EventsManager.getInstance().activeOption(eInfo);
    }

    protected void onNightStart(World world) {
        EventInfo eInfo = new EventInfo(new FakeNightEvent());
        eInfo.setWorld(Optional.of(world));
        eInfo.getPlaceholders().put("%world%", world.getName());
        eInfo.setOption(Option.WORLD_NIGHT);
        EventsManager.getInstance().activeOption(eInfo);
    }
}
class FakeDayEvent extends Event {
    @Override
    public @NotNull HandlerList getHandlers() {
        return null;
    }
}
class FakeNightEvent extends Event {
    @Override
    public @NotNull HandlerList getHandlers() {
        return null;
    }
}