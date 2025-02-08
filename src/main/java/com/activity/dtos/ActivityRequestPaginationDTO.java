package com.activity.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequestPaginationDTO {

    private int page = 0;
    private int size = 2;
    private String fullName;
    private String clientFullName;
    private String status;
}
