package com.petrovpavel.localservice.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Location.
 *
 * @author Pavel_Petrov
 */
@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(exclude = {"id"})
public class Location {

    /**
     * Id.
     */
    @Id
    private Long id;

    /**
     * Идентификатор записи ТС.
     */
    @Column
    private String city;

    /**
     * Марка ТС.
     */
    @Column
    private String street;

    /**
     * Номер ТС.
     */
    @Column
    private String house;

    /**
     * Код ФИАС
     */
    @Column
    private String fiasCode;

}
