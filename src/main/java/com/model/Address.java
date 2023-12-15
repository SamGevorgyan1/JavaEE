package com.model;


import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "address")
@Builder
public class Address {
    @Column(name = "address_id")
    private int addressId;
    private String country;
    private String city;
    private String street;
    private String home;
    @Column(name = "user_id")
    private int userId;
}
