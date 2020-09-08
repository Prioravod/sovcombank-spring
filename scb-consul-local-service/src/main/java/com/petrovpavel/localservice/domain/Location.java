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
    private Integer id;

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
    @Column(columnDefinition = "fias_code")
    private String fiasCode;

}
