package fr.eni.ludotheque.bo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="LOCATIONS")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    @NonNull
    @Column(nullable=false)
    private Date startingDateAt;
    
    private Date returningDateAt;
    private Float dayPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // Peut être nul
    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = true)
    private Bill bill;

    // Ne peut pas être nul
    @ManyToOne
    @JoinColumn(name = "exemplary_id", nullable = false)
    private Exemplary exemplary;

}
