package com.resturant.food_ordering.controllers;

import com.resturant.food_ordering.controllers.dto.TableStatusResponse;
import com.resturant.food_ordering.models.Order;
import com.resturant.food_ordering.services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping("/{tableNumber}/orders")
    public ResponseEntity<List<Order>> getOrdersForTable(@PathVariable Integer tableNumber) {
        return ResponseEntity.ok(tableService.getOrdersForTable(tableNumber));
    }

    @GetMapping("/{tableNumber}/active-orders")
    public ResponseEntity<List<Order>> getActiveOrdersForTable(@PathVariable Integer tableNumber) {
        return ResponseEntity.ok(tableService.getActiveOrdersForTable(tableNumber));
    }

    @GetMapping("/{tableNumber}/status")
    public ResponseEntity<TableStatusResponse> getTableStatus(@PathVariable Integer tableNumber) {
        boolean occupied = tableService.isTableOccupied(tableNumber);
        return ResponseEntity.ok(new TableStatusResponse(tableNumber, occupied));
    }

    @GetMapping
    public ResponseEntity<java.util.List<TableStatusResponse>> listTables() {
        return ResponseEntity.ok(tableService.listTables());
    }

    @PostMapping("/{tableNumber}/occupy")
    public ResponseEntity<TableStatusResponse> occupyTable(@PathVariable Integer tableNumber) {
        tableService.occupyTable(tableNumber);
        return ResponseEntity.ok(new TableStatusResponse(tableNumber, true));
    }

    @PostMapping("/{tableNumber}/free")
    public ResponseEntity<TableStatusResponse> freeTable(@PathVariable Integer tableNumber) {
        tableService.freeTable(tableNumber);
        return ResponseEntity.ok(new TableStatusResponse(tableNumber, false));
    }
}
