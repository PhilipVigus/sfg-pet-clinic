package com.philvigus.sfgpetclinic.services.springdatajpa;

import com.philvigus.sfgpetclinic.model.Owner;
import com.philvigus.sfgpetclinic.repositories.OwnerRepository;
import com.philvigus.sfgpetclinic.repositories.PetRepository;
import com.philvigus.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSdJpaServiceTest {
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSdJpaService ownerSdJpaService;

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> foundOwners = ownerSdJpaService.findAll();

        assertEquals(2, owners.size());
    }

    @Test
    void findById() {
        Long id = 1L;
        String lastName = "Smith";

        Owner owner = Owner.builder().id(id).lastName(lastName).build();

        when(ownerRepository.findById(any())).thenReturn(Optional.of(owner));

        Owner ownerFoundById = ownerSdJpaService.findById(id);

        assertNotNull(ownerFoundById);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();

        when(ownerRepository.save(any())).thenReturn(ownerToSave);

        Owner savedOwner = ownerSdJpaService.save(ownerToSave);

        assertNotNull(savedOwner);

        verify(ownerRepository, times(1)).save(ownerToSave);
    }

    @Test
    void delete() {
        Owner ownerToDelete = Owner.builder().build();

        ownerSdJpaService.delete(ownerToDelete);

        verify(ownerRepository, times(1)).delete(ownerToDelete);
    }

    @Test
    void deleteById() {
        long id = 1L;

        Owner ownerToDelete = Owner.builder().id(id).build();

        ownerSdJpaService.deleteById(ownerToDelete.getId());

        verify(ownerRepository, times(1)).deleteById(id);
    }

    @Test
    void findByLastName() {
        Long id = 1L;
        String lastName = "Smith";

        Owner owner = Owner.builder().id(id).lastName(lastName).build();

        when(ownerRepository.findByLastName(any())).thenReturn(owner);

        Owner foundOwner = ownerSdJpaService.findByLastName(lastName);

        assertEquals(id, foundOwner.getId());
    }
}