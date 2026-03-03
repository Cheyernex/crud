package com.entity;

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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "SUPPLIER_PAYMENT"
)
public class SupplierPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "CD_PAYMENT")
    private Long cdPayment;

    @Column(name = "NU_PAYMENT")
    private Long nuPayment;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "CURRENCY", length = 3, nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "PAYMENT_METHOD")
    private TpPayment paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "ST_PAYMENT")
    private StPayment stPayment;

    @Column(name = "DT_PAYMENT", nullable = false, unique = true)
    private LocalDateTime dtPayment;

    @Column(name = "DS_NOTES")
    private String dsNotes;

    @Version
    @Column(name = "NU_VERSION")
    private Long version;

    @CreationTimestamp
    @Column(name = "DT_CREATION",nullable = false, updatable = false)
    private LocalDateTime dtCreation;

    @UpdateTimestamp
    @Column(name = "DT_MODIFICATION")
    private LocalDateTime dtModification;

    public enum TpPayment{
        CHEQUE,
        TRANSFERENCIA,
        EFECTIVO
    }

    public enum StPayment{
        PENDIENTE,
        CONFIRMADO,
        CANCELADO
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_SUPPLIER", nullable = false)
    @JsonIgnore
    private Supplier supplier;

    @OneToMany(
            mappedBy = "supplierPayment",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<SupplierPaymentDetail> supplierPaymentsDetail = new ArrayList<>();

}