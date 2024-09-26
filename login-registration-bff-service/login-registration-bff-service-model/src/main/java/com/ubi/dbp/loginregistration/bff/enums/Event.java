package com.ubi.dbp.loginregistration.bff.enums;

public enum Event {
    REGISTRATION("registration"),
    FORGET_LOGIN_PIN("forgetloginpin"),
    EXPIRED_LOGIN_PIN("expiredloginpin");
    private final String event;
    Event(String event) {
        this.event = event;
    }
    public String getEventType() {
        return event;
    }
    public static boolean isValidEvent(String eventType) {
        for (Event event : values()) {
            if (event.getEventType().equalsIgnoreCase(eventType)) {
                return true;
            }
        }
        return false;
    }
}