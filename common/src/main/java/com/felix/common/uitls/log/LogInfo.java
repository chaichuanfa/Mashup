package com.felix.common.uitls.log;

class LogInfo {

    private int priority;

    private String tag;

    private String message;

    LogInfo(int priority, String tag, String message) {
        this.priority = priority;
        this.tag = tag;
        this.message = message;
    }

    int getPriority() {
        return priority;
    }

    String getTag() {
        return tag;
    }

    String getMessage() {
        return message;
    }
}
