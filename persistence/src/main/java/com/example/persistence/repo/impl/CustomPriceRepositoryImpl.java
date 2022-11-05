package com.example.persistence.repo.impl;

import com.example.persistence.entities.Brand;
import com.example.persistence.entities.Price;
import com.example.persistence.entities.Product;
import com.example.persistence.repo.CustomPriceRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;

public class CustomPriceRepositoryImpl implements CustomPriceRepository {

    private static final String PRODUCT = "product";
    private static final String BRAND = "brand";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String PRIORITY = "priority";


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Price getPriceByDateProductBrand(LocalDateTime dateTime, Product product, Brand brand) {

        CriteriaBuilder cB = entityManager.getCriteriaBuilder();

        CriteriaQuery<Price> cq = cB.createQuery(Price.class);
        Root<Price> r = cq.from(Price.class);

        Predicate pDate = cB.between(cB.parameter(LocalDateTime.class, "dateTime"),r.get(START_DATE),r.get(END_DATE));
        Predicate pProduct = cB.equal(cB.parameter(Product.class, PRODUCT), r.get(PRODUCT));
        Predicate pBrand = cB.equal(cB.parameter(Brand.class, BRAND), r.get(BRAND));

        cq.select(r);
        cq.where(pDate,pProduct,pBrand);
        // Los valores de prioridad mayor se cogen antes
        cq.orderBy(cB.desc(r.get(PRIORITY)));


        TypedQuery<Price> query = entityManager.createQuery(cq);
        // Una única tarifa
        query.setMaxResults(1);
        query.setParameter("dateTime", dateTime);
        query.setParameter(PRODUCT, product);
        query.setParameter(BRAND, brand);
        return query.getSingleResult();
    }
}
