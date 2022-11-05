package com.example.persistence.entities;

import com.example.persistence.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "BRAND")
public class Brand extends BaseEntity {

    private Long idBrand;
    private String name;

    @Id
    @Column (name = "ID_BRAND", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Long idBrand) {
        this.idBrand = idBrand;
    }

    @Column (name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
