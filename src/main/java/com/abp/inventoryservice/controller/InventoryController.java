package com.abp.inventoryservice.controller;

import com.abp.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "${inventory.baseUrl}")
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping(value = "/bookInventory")
    public ResponseEntity bookInventory(@RequestParam(value="inventoryId") Long inventoryId, @RequestParam(value="count") Long count) {
            inventoryService.reduceInventoryByAmount(inventoryId, count);
        return ResponseEntity.ok("Inventory book OK");
    }
}
