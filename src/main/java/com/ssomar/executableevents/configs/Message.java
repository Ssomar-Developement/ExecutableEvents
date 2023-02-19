package com.ssomar.executableevents.configs;

import com.ssomar.score.configs.messages.MessageInterface;

public enum Message implements MessageInterface {

    REQUIRE_PERMISSION("requirePermission");

    private String name;

    Message(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
