package com.philvigus.sfgpetclinic.services.map;

import com.philvigus.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {
    OwnerMapService ownerMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
    }

    @Test
    void findAll() {
        ownerMapService.save(Owner.builder().id(1L).build());

        Set<Owner> owners = ownerMapService.findAll();

        assertEquals(1, owners.size());
    }

    @Test
    void findById() {
        Long ownerId = 1L;

        ownerMapService.save(Owner.builder().id(ownerId).build());

        Owner owner = ownerMapService.findById(ownerId);

        assertEquals(1L, owner.getId());
    }

    @Test
    void saveWithGivenId() {
        Long ownerId = 1L;

        Owner savedOwner = ownerMapService.save(Owner.builder().id(ownerId).build());

        assertEquals(ownerId, savedOwner.getId());
    }

    @Test
    void saveWithNoId() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());

        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        ownerMapService.delete(savedOwner);

        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        Long savedOwnerId = 1L;
        Long ownerToBeDeletedId = 2L;

        Owner savedOwner = ownerMapService.save(Owner.builder().id(savedOwnerId).build());
        Owner ownerToBeDeleted = ownerMapService.save(Owner.builder().id(ownerToBeDeletedId).build());

        ownerMapService.deleteById(ownerToBeDeletedId);

        Owner remainingOwner = ownerMapService.findById(savedOwnerId);

        assertEquals(1, ownerMapService.findAll().size());
        assertEquals(savedOwnerId, remainingOwner.getId());
    }

    @Test
    void findByLastName() {
        Long ownerId = 1L;
        String lastNameToBeFound = "Smith";

        Owner ownerToBeFound = ownerMapService.save(Owner.builder().id(ownerId).lastName(lastNameToBeFound).build());
        ownerMapService.save(Owner.builder().lastName("Jones").build());

        Owner owner = ownerMapService.findByLastName(lastNameToBeFound);

        assertEquals(ownerId, owner.getId());
    }
}