package com.entity.Suppliers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "SUPPLIER_CONTACT",
        indexes = {
                @Index(name = "IDX_SUPPLIER_CONTACT",
                        columnList = "CD_SUPPLIER"),
                    @Index(name = "IDX_SUPPLIER_MAIN_CONTACT",
                            columnList = "CD_SUPPLIER, ST_MAIN"
                )
        }
)

public class SupplierContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CD_CONTACT")
    private Long cdContact;

    @NotBlank
    @Column(name = "NM_CONTACT", nullable = false, length = 50)
    private String nmContact;

    @Email
    @NotBlank
    @Column(name = "DS_EMAIL", nullable = false, length = 120)
    private  String dsEmail;

    @NotBlank
    @Column(name = "DS_PHONE", nullable = false, length = 20)
    @Size(min = 7, max = 20)
    private String dsPhone;

    @NotBlank
    @Column(name = "DS_POSITION", nullable = false, length = 50)
    private String dsPosition;

    @Column(name = "ST_MAIN", nullable = false)
    private boolean stMain = false;

    @Column(name = "ST_ACTIVE", nullable = false)
    private boolean stActive = true;

    @CreationTimestamp
    @Column(name = "DT_CREATION", updatable = false)
    private LocalDateTime dtCreation;

    @UpdateTimestamp
    @Column(name = "DT_MODIFICATION")
    private LocalDateTime dtModification;

    /*
        FK -> SUPPLIER.CD_SUPPLIER
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_SUPPLIER", nullable = false)
    @JsonIgnore
    private Supplier supplier;
}