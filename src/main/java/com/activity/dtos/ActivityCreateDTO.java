package com.activity.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityCreateDTO {

    private String idDocument;

    private String fullName;

    private String clientFullName;

    private String clientPhone;

    private Double amount;

    private Double customerPayment;

    private String description;

    private String note;

    private String status;
}
