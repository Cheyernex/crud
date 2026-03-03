package com.entity;

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

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name="SUPPLIER_INVOICE_DETAIL"
)
public class SupplierInvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CD_INVOICE_DETAIL")
    private Long cdInvoiceDetail;

    @Column(name="DS_DETAIL", nullable = false)
    private String dsDetail;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    private BigDecimal quantity;

    @NotNull
    @Column(name = "UNIT_PRICE", nullable = false)
    private BigDecimal unitPrice;

    @NotNull
    @Column(name = "TAX_AMOUNT", nullable = false)
    private BigDecimal taxAmount = BigDecimal.ZERO;

    @NotNull
    @Column(name = "LINE_TOTAL")
    private BigDecimal lineTotal;

    @Column(name = "ACCOUNT_CODE")
    private String accountCode;

    @Version
    @Column(name = "NU_VERSION")
    private Long version;

    @CreationTimestamp
    @Column(name = "DT_CREATION", updatable = false, nullable = false)
    private LocalDateTime dtCreation;

    @UpdateTimestamp
    @Column(name = "DT_MODIFICATION", nullable = false)
    private LocalDateTime dtModification;

    public BigDecimal calculateLineTotal() {
        if (quantity == null || unitPrice == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal subtotal = unitPrice.multiply(quantity);
        BigDecimal tax = taxAmount != null ? taxAmount : BigDecimal.ZERO;
        return subtotal.add(tax);
    }

    @PrePersist
    @PreUpdate
    public void updateLineTotal(){
        this.lineTotal = calculateLineTotal();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_INVOICE", nullable = false)
    @JsonIgnore
    private SupplierInvoice supplierInvoice;

}