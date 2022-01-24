package com.philvigus.sfgpetclinic.bootstrap;

import com.philvigus.sfgpetclinic.model.*;
import com.philvigus.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;
    private final SpecialityService specialityService;
    private final VetService vetService;
    private final VisitService visitService;

    public DataLoader(PetTypeService petTypeService,
                      OwnerService ownerService,
                      SpecialityService specialityService,
                      VetService vetService, VisitService visitService) {
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
        this.specialityService = specialityService;
        this.vetService = vetService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int numberOfPetTypes = petTypeService.findAll().size();

        if (numberOfPetTypes == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");

        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Here Street");
        owner1.setCity("Theresville");
        owner1.setTelephone("0123 45678");

        Pet owner1Pet = new Pet();
        owner1Pet.setPetType(savedDogPetType);
        owner1Pet.setOwner(owner1);
        owner1Pet.setBirthDate(LocalDate.now());
        owner1Pet.setName("Rosco");

        owner1.getPets().add((owner1Pet));

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("456 There Road");
        owner2.setCity("Somewhereford");
        owner2.setTelephone("0987 65432");

        Pet owner2Pet = new Pet();
        owner2Pet.setPetType(savedCatPetType);
        owner2Pet.setOwner(owner2);
        owner2Pet.setBirthDate(LocalDate.now());
        owner2Pet.setName("Eric");

        owner2.getPets().add((owner2Pet));

        ownerService.save(owner2);

        Visit owner2Visit = new Visit();
        owner2Visit.setPet(owner2Pet);
        owner2Visit.setDate(LocalDate.now());
        owner2Visit.setDescription("Sneezy kitty");

        visitService.save(owner2Visit);

        System.out.println("Loaded Owners....");

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");

        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");

        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");

        Speciality savedDentistry = specialityService.save(dentistry);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}
