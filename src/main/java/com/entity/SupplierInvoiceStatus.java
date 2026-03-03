package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "SUPPLIER_INVOICE_STATUS"
)
public class SupplierInvoiceStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_INVOICE_STATUS")
    private Long cdInvoiceStatus;

    @Enumerated (EnumType.STRING)
    @Column(name = "ST_STATUS")
    private TyStatus tyStatus;

    @UpdateTimestamp
    @Column(name = "DT_CHANGE")
    private LocalDateTime dtChange;

    @Column(name = "DS_OBSERVATION")
    private String dsObservation;

    public enum TyStatus{
        PENDING,
        PARTIAL,
        PAID,
        CANCELLED
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_INVOICE")
    @JsonIgnore
    private SupplierInvoice supplierInvoice;

}