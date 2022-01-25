package com.philvigus.sfgpetclinic.services.map;

import com.philvigus.sfgpetclinic.model.Owner;
import com.philvigus.sfgpetclinic.model.Pet;
import com.philvigus.sfgpetclinic.model.PetType;
import com.philvigus.sfgpetclinic.services.OwnerService;
import com.philvigus.sfgpetclinic.services.PetService;
import com.philvigus.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        if (owner == null) {
            return null;
        }

        if (owner.getPets() != null) {
            owner.getPets().forEach(pet -> {
                if (pet.getPetType() == null) {
                    throw new RuntimeException("Pet Type is required");
                }

                if (pet.getPetType().getId() == null) {
                    PetType petType = petTypeService.save(pet.getPetType());
                    pet.setPetType(petType);
                }

                if (pet.getId() == null) {
                    Pet savedPet = petService.save(pet);
                    pet.setId(savedPet.getId());
                }
            });
        }

        return super.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        super.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
