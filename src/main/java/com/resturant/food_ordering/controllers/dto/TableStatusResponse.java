package com.resturant.food_ordering.controllers.dto;

public class TableStatusResponse {
    private Integer tableNumber;
    private boolean occupied;

    public TableStatusResponse(Integer tableNumber, boolean occupied) {
        this.tableNumber = tableNumber;
        this.occupied = occupied;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }
}
