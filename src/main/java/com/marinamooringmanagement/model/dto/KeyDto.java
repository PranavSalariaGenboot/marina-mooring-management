package com.marinamooringmanagement.model.dto;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
@ToString
@EqualsAndHashCode
public abstract class KeyDto implements Serializable {

    private static final long serialVersionUID = 2642629054722973598L;
    private Integer id;
    protected Date creationDate;
    private Date lastModifiedDate;

}

