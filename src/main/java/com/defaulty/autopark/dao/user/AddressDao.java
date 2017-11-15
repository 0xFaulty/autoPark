package com.defaulty.autopark.dao.user;

import com.defaulty.autopark.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressDao extends JpaRepository<Address, Long> {
}
