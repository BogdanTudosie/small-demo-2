package com.bogdan.small_demo_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDto {
    private String uuid;
    private String name;
    private String osVersion;
}
