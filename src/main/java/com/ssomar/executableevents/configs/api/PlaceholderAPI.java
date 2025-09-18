package com.ssomar.executableevents.configs.api;

import org.bukkit.Bukkit;

public class PlaceholderAPI {

    // TRUE = FREE
    private static final boolean placeOfWork = false;
    final String uid = "%%__USER__%%";
    final String rid = "%%__RESOURCE__%%";
    final String nonce = "%%__NONCE__%%";

    public static boolean isLotOfWork() {
        return placeOfWork;
    }
}
