package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="CUSTOMERS")
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)// pas obligatoire !!
    private Long customerId;
    @NonNull
    @Column(nullable=false, length=50)
    private String firstName;
    @NonNull
    @Column(nullable=false, length=50)
    private String lastName;
    @NonNull
    @Column(nullable=false, length=50)
    private String email;

    @Column(length=10, unique=true)
    private String phone;

    // On créé la relation OneToOne UNIDIRECTIONNELLE entre Customer et Address
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

}
