package com.abp.inventoryservice.controller;

import com.abp.inventoryservice.service.InventoryService;
import com.abp.inventoryservice.service.InventoryServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1")
// TODO: refactor to use the one in application yml
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping(value = "/bookInventory")
    public ResponseEntity bookInventory(@RequestParam(value="inventoryId") Long inventoryId, @RequestParam(value="count") Long count) {
        try {
            inventoryService.reduceInventoryByAmount(inventoryId, count);
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Inventory book OK");
    }
}
