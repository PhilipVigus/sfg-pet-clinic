package com.philvigus.sfgpetclinic.services.map;

import com.philvigus.sfgpetclinic.model.Speciality;
import com.philvigus.sfgpetclinic.model.Vet;
import com.philvigus.sfgpetclinic.services.SpecialityService;
import com.philvigus.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        vet.getSpecialities().forEach(speciality -> {
            if (speciality.getId() == null) {
                Speciality savedSpeciality = specialityService.save((speciality));
                speciality.setId(savedSpeciality.getId());
            }
        });

        return super.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        super.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
