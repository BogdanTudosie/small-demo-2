package com.bogdan.small_demo_2.inventoryservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bogdan.small_demo_2.dto.InventoryDto;
import com.bogdan.small_demo_2.inventoryservice.service.InventoryService;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryServiceController {
    private final InventoryService inventoryService;

    public InventoryServiceController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/fetch")
    public InventoryDto fetchInventory(@RequestParam String deviceId) {
        return inventoryService.fetchAndStore(deviceId);
    }

    @GetMapping("/device")
    public InventoryDto getInventory(@RequestParam("device") String device) {
        return inventoryService.getInventory(device);
    }

    @GetMapping("/all")
    public List<InventoryDto> getAllInventories() {
        return inventoryService.getAllInventories();
    } 
}
