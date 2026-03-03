package com.entity.Suppliers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "SUPPLIER_PAYMENT_DETAIL"
)
public class SupplierPaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_SUPPLIER_PAYMENT_DETAIL")
    private Long cdSupplierPaymentDetail;

    @Column(name = "AMOUNT_APPLIED", nullable = false, precision = 18, scale = 2)
    private BigDecimal amountApplied;

    @Version
    @Column(name = "NU_VERSION")
    private Long version;

    @CreationTimestamp
    @Column(name = "DT_CREATION", updatable = false)
    private LocalDateTime dtCreation;

    @UpdateTimestamp
    @Column(name = "DT_MODIFICATION")
    private LocalDateTime dtModification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_PAYMENT", nullable = false)
    @JsonIgnore
    private SupplierPayment supplierPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_INVOICE", nullable = false)
    @JsonIgnore
    private SupplierInvoice supplierInvoice;

}
