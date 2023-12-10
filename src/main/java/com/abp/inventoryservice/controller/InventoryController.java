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
            // TODO need to reconsider on error return, so that there's a clear distinction compare to other unexpected error
            // where is it more to notice on low inventory
            return ResponseEntity.internalServerError().body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Unexpected error: " + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

        return ResponseEntity.ok("Inventory book OK");
    }
}
