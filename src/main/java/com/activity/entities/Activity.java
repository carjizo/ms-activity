package com.activity.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Size(max = 25)
    @NotBlank
    @Column(name = "id_document")
    private String idDocument;

    @Size(max = 100)
    @Column(name = "full_name")
    private String fullName;

    @Size(max = 100)
    @Column(name = "client_full_name")
    private String clientFullName;

    @Size(max = 50)
    @Column(name = "client_phone")
    private String clientPhone;

    @DecimalMin("0.0")
//    @NotBlank
    @Column(name = "amount")
    private Double amount;

    @DecimalMin("0.0")
    @Column(name = "customer_payment")
    private Double customerPayment;

    @Size(max = 255)
    @NotBlank
    @Column(name = "description")
    private String description;

    @Size(max = 255)
    @Column(name = "note")
    private String note;

    @Size(max = 20)
    @NotBlank
    @Column(name = "status")
    private String status;

    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        ZoneId zoneId = ZoneId.of("America/Lima");
        createdAt = OffsetDateTime.now(zoneId);
        updatedAt = OffsetDateTime.now(zoneId);
    }

    @PreUpdate
    protected void onUpdate() {
        ZoneId zoneId = ZoneId.of("America/Lima");
        updatedAt = OffsetDateTime.now(zoneId);
    }
}
