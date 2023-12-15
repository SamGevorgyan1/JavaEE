package com.repository;

import com.model.Address;

public interface AddressRepository {
     Address saveAddress(Address address);
     Address getAddressByUserId(int userId);
     void updateAddress(Address address);
}
