package com.entity.Suppliers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "SUPPLIER_ADDRESS",
        indexes = {
                @Index(name = "IDX_SUPPLIER_ADDRESS", columnList = "CD_SUPPLIER, TP_ADDRESS")
        }
)
public class SupplierAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_ADDRESS")
    private Long cdAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TP_ADDRESS", nullable = false)
    private TpAddress tpAddress;

    @Column(name = "DS_ADDRESS", nullable = false, length = 200)
    private String dsAddress;

    @Column(name = "DS_SECTOR", length = 80)
    private String dsSector;

    @NotBlank
    @Column(name = "DS_CITY", nullable = false, length = 80)
    private String dsCity;

    @NotBlank
    @Column(name = "DS_STATE", nullable = false, length = 80)
    private String dsState;

    @NotBlank
    @Column(name = "DS_COUNTRY", nullable = false, length = 80)
    private String dsCountry;

    @Column(name = "DS_ZIP_CODE", length = 10)
    private String dsZipCode;

    @Column(name = "ST_ACTIVE", nullable = false)
    private boolean stActive = true;

    @CreationTimestamp
    @Column(name = "DT_CREATION", updatable = false, nullable = false)
    private LocalDateTime dtCreation;

    @UpdateTimestamp
    @Column(name = "DT_MODIFICATION", nullable = false)
    private LocalDateTime dtModification;

    public enum TpAddress{
        FISCAL,
        COMERCIAL,
        DESPACHO
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_SUPPLIER", nullable = false)
    @JsonIgnore
    private Supplier supplier;
}