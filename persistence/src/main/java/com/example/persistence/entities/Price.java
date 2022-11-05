package com.example.persistence.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

import com.example.persistence.BaseEntity;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "PRICES")
public class Price extends BaseEntity {

    private Long priceList;
    private Brand brand;
    private Product product;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priority;
    private Double rate;
    private String curr;

    @Id
    @Column(name = "PRICE_LIST", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }

    @ManyToOne
    @JoinColumn (name = "ID_BRAND", nullable = false)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @ManyToOne
    @JoinColumn (name = "PRODUCT_ID", nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column (name = "START_DATE", nullable = false)
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Column (name = "END_DATE", nullable = false)
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Column (name = "PRIORITY")
    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Column (name = "PRICE")
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Column (name = "CURR", length = 3)
    @Length (min=3, max=3)
    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }




}
