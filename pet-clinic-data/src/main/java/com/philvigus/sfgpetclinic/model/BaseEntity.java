package com.philvigus.sfgpetclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    /**
     * Use a box type here rather than the long primitive because Hibernate recommends
     * it. It allows ids to be set to null if necessary, which isn't possible with long
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
