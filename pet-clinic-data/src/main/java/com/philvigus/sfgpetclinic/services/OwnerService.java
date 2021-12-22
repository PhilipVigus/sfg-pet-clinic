package com.philvigus.sfgpetclinic.services;

import com.philvigus.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
