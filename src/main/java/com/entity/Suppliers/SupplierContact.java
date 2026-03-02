package com.entity.Suppliers;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "SUPPLIER_CONTACT",
        indexes = {
                @Index(name = "IDX_SUPPLIER_CONTACT", columnList = "CD_SUPPLIER")
        }
)

public class SuppliersContacts {
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
    private String dsPhone;

    @NotBlank
    @Column(name = "DS_POSITION", nullable = false, length = 50)
    private String dsPosition;

    @Column(name = "ST_MAIN", nullable = false)
    private boolean stMain = false;

    @Column(name = "ST_STATUS", nullable = false)
    private boolean stStatus = true;

    /*
        FK -> SUPPLIER.CD_SUPPLIER
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CD_SUPPLIER", nullable = false)
    private Supplier supplier;
}
