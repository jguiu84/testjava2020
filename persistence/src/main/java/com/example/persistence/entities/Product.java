package com.example.persistence.entities;

import com.example.persistence.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product extends BaseEntity {

    private Long idProduct;
    private String productName;

    @Id
    @Column(name = "PRODUCT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    @Column (name = "NAME", nullable = false)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
