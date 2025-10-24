package com.bogdan.small_demo_2.dto;

import java.time.Instant;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDto {
    private DeviceDto device;
    private List<AppDto> apps;
    private Instant collectedAt; // ISO 8601 format
    private String source = "MDM_SERVICE";
}
