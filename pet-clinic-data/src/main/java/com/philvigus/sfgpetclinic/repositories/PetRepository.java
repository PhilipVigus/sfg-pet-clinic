package com.philvigus.sfgpetclinic.repositories;

import com.philvigus.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
