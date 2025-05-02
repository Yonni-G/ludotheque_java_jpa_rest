package fr.eni.ludotheque.dto;

import fr.eni.ludotheque.bo.Address;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name="CUSTOMERS")
public class CustomerDTO {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;

    @NonNull
    private String phone;

    @NonNull
    private Address address;

}
