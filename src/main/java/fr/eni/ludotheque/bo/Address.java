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
@Table(name="ADDRESSES")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NonNull
    @Column(nullable=false, length=50)
    private String street;

    @NonNull
    @Column(nullable=false, length=5)
    private String postalCode;

    @NonNull
    @Column(nullable=false, length=25)
    private String city;

}
