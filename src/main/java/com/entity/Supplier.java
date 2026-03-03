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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "SUPPLIER",
        indexes = {
                @Index(name = "IDX_SUPPLIER_NAME",
                        columnList = "NM_RAZONSOCIAL"),
                    @Index(name = "IDX_SUPPLIER_DOC_TYPE",
                            columnList = "TP_SUPPLIER_DOCUMENT, NU_SUPPLIER_DOCUMENT")
            },
        uniqueConstraints =
                {
                    @UniqueConstraint(
                            name = "UK_SUPPLIER_DOC",
                            columnNames = {"TP_SUPPLIER_DOCUMENT", "NU_SUPPLIER_DOCUMENT"}
                    )
                }
        )
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "CD_SUPPLIER")
    private Long cdSupplier;

    @Column(name="CD_SUPPLIER_CODE", length=20, unique=true)
    private String cdSupplierCode;

    @NotBlank
    @Column (name = "NM_RAZONSOCIAL", nullable = false, length = 150)

    private String nmRazonSocial;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column (name = "TP_SUPPLIER", nullable = false)
    private TpSupplier tpSupplier;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column (name = "TP_SUPPLIER_DOCUMENT", nullable = false)
    private TpDocument tpSupplierDocument;

    @NotBlank
    @Column (name = "NU_SUPPLIER_DOCUMENT", nullable = false, length = 20)
    private String nuSupplierDocument;

    @Column (name = "REG_MERCHANT", length = 30)
    private String regMerchant;

    @PositiveOrZero
    @Column(name = "NU_CREDIT_DAYS")
    private Integer nuCreditDays;

    @Column(name = "ST_ACTIVE", nullable = false)
    private boolean stActive = true;

    @Version
    @Column(name = "NU_VERSION")
    private Long version;

    @CreationTimestamp
    @Column(name = "DT_CREATION", updatable = false, nullable = false)
    private LocalDateTime dtCreation;

    @UpdateTimestamp
    @Column(name = "DT_MODIFICATION", nullable = false)
    private LocalDateTime dtModification;

    public enum TpSupplier{
        PERSONA_FISICA,
        PERSONA_JURIDICA
    }

    public enum TpDocument{
        CED,
        RNC,
        PASS
    }

    @OneToMany(
            mappedBy = "supplier",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<SupplierContact> supplierContacts = new ArrayList<>();

    @OneToMany(
            mappedBy = "supplier",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<SupplierAddress> supplierAddresses = new ArrayList<>();

    @OneToMany(
            mappedBy = "supplier",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<SupplierInvoice> supplierInvoices = new ArrayList<>();

    @OneToMany(
            mappedBy = "supplier",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<SupplierPayment> supplierPayments = new ArrayList<>();

}