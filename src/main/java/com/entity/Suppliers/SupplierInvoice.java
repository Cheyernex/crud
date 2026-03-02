package com.entity.Suppliers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name="SUPPLIER_INVOICE",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_SUPPLIER_INVOICE",
                        columnNames = {"NU_INVOICES", "CD_SUPPLIER"}
                )
        }
)
public class SupplierInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_INVOICES")
    private Long cdInvoices;

    @NotNull
    @Column(name = "NU_INVOICES", nullable = false)
    private Long nuInvoices;

    @NotNull
    @Column(name = "INVOICE_DATE", updatable = false, nullable = false)
    private LocalDateTime invoiceDate;

    @NotNull
    @Column(name = "DUE_DATE", nullable = false)
    private LocalDate dueDate;

    @NotNull
    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private BigDecimal totalAmount;

    @NotNull
    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ST_INVOICES", nullable = false)
    private TyStatus stInvoices;

    @Column(name="CURRENCY")
    private String currency;

    @Column(name = "TAX_AMOUNT")
    private BigDecimal taxAmount;

    @Version
    @Column(name = "NU_VERSION")
    private Long version;

    @CreationTimestamp
    @Column(name = "DT_CREATION", updatable = false, nullable = false)
    private LocalDateTime dtCreation;

    @UpdateTimestamp
    @Column(name = "DT_MODIFICATION", nullable = false)
    private LocalDateTime dtModification;

    public enum TyStatus{
        PENDING,
        PARTIAL,
        PAID,
        CANCELLED
    }

    @OneToMany(
            mappedBy = "supplierInvoice",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<SupplierInvoiceDetail> supplierInvoiceDetails = new ArrayList<>();

    @OneToMany(
            mappedBy = "supplierInvoice",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<SupplierInvoiceStatus> supplierInvoiceStatuses = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_SUPPLIER", nullable = false)
    @JsonIgnore
    private Supplier supplier;
}