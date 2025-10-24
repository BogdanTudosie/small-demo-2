package com.bogdan.small_demo_2.mdm_service.client;

import org.springframework.stereotype.Service;

import com.bogdan.small_demo_2.dto.AppDto;
import com.bogdan.small_demo_2.dto.DeviceDto;
import com.bogdan.small_demo_2.dto.InventoryDto;
import com.bogdan.small_demo_2.mdm_service.interfaces.MdmService;

import java.util.List;

@Service
public class MdmServiceImpl implements MdmService {
    
    @Override
    public InventoryDto fetchInventory(String deviceId) {
        // Mock implementation
        DeviceDto device = new DeviceDto(deviceId, "Bogdan's iPhone", "iOS 26.0");

        // Create some App DTOs
        AppDto safari = new AppDto("Apple", "Safari", "16.0");
        AppDto mail = new AppDto("Apple", "Mail", "16.0");
        AppDto whatsapp = new AppDto("Meta", "WhatsApp", "2.23.10");
        AppDto spotify = new AppDto("Spotify Ltd.", "Spotify", "8.7.0");

        List<AppDto> apps = List.of(safari, mail, whatsapp, spotify);

        InventoryDto inventory = new InventoryDto();
        inventory.setDevice(device);
        inventory.setApps(apps);
        inventory.setCollectedAt(java.time.Instant.now());
        return inventory;
    }
}
