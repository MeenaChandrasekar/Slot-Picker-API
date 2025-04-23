package com.optimization.slotpicker.model;

import java.awt.Container;
import java.util.List;

public class PickSpotRequest {
    private Container container;
    private List<YardSlot> yardMap;

    // Getters and setters
    public Container getContainer() { return container; }
    public void setContainer(Container container) { this.container = container; }

    public List<YardSlot> getYardMap() { return yardMap; }
    public void setYardMap(List<YardSlot> yardMap) { this.yardMap = yardMap; }
}
