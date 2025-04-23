package com.optimization.slotpicker.model;

public class PickSpotResponse {
    private String containerId;
    private int targetX;
    private int targetY;
    private String error; // Optional, if no slot is available

    // Getters and setters
    public String getContainerId() { return containerId; }
    public void setContainerId(String containerId) { this.containerId = containerId; }

    public int getTargetX() { return targetX; }
    public void setTargetX(int targetX) { this.targetX = targetX; }

    public int getTargetY() { return targetY; }
    public void setTargetY(int targetY) { this.targetY = targetY; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}
