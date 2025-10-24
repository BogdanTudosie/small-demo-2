package com.bogdan.small_demo_2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppDto {
    private String vendor;
    private String name;
    private String version;
}
