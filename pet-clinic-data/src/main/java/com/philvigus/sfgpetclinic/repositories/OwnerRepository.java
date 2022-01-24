package com.philvigus.sfgpetclinic.repositories;

import com.philvigus.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
