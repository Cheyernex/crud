package com.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
        name = "suppliers",
        indexes = {
            @Index(name = "IDX_SUPPLIER_DOCUMENT",
                    columnList = "NU_SUPPLIER_DOCUMENT"),
                @Index(name = "IDX_SUPPLIER_NAME",
                        columnList = "NM_RAZONSOCIAL"),
                    @Index(name = "IDX_SUPPLIER_DOC_TYPE",
                            columnList = "TP_SUPPLIER_DOCUMENT, NU_SUPPLIER_DOCUMENT")
            }
        )
public class Suppliers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "CD_SUPPLIER")
    private Long cdSupplier;

    @Column (name = "NM_RAZONSOCIAL", nullable = false, length = 150)
    @NotBlank
    private String nmRazonSocial;

    @Enumerated(EnumType.STRING)
    @Column (name = "TP_SUPPLIER", nullable = false)
    @NotNull
    private tpSupplier tpSupplier;

    @Enumerated(EnumType.STRING)
    @Column (name = "TP_SUPPLIER_DOCUMENT", nullable = false)
    @NotNull
    private tpDocument tpSupplierDocument;

    @Column (name = "NU_SUPPLIER_DOCUMENT", nullable = false, unique = true, length = 20)
    @NotBlank
    private String nuSupplierDocument;

    @Column (name = "REG_MERCHANT", length = 30)
    private String regMerchant;

    @PositiveOrZero
    @Column(name = "NU_CREDIT_DAYS")
    private Integer nuCreditDays;

    @CreationTimestamp
    @Column(name = "DT_CREATION", updatable = false, nullable = false)
    private LocalDateTime dtCreation;

    @UpdateTimestamp
    @Column(name = "DT_MODIFICATION", nullable = false)
    private LocalDateTime dtModification;

    @Column(name = "ST_SUPPLIER")
    private boolean active = true;

    public enum tpSupplier{
        PERSONA_FISICA,
        PERSONA_JURIDICA
    }

    public enum tpDocument{
        CED,
        RNC,
        PASS
    }

}