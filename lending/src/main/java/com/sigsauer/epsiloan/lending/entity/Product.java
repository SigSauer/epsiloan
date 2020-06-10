package com.sigsauer.epsiloan.lending.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * Product is entity-class, that describes loan product that sets the rules for calculating the interests rate.
 *
 * @see ProductProperties
 *
 * @author SigSauer
 * @version 1.0
 */
@Data
@Entity
public class Product {

    //General Info

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String description = "Description";

    //Service Info

    @ManyToOne(targetEntity = DefaultUser.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private final DefaultUser creatingUser;

    private final LocalDate creatingDate;

    //Configuration Info

    @OneToOne(targetEntity = ProductProperties.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private ProductProperties properties = new ProductProperties();

    private Product(DefaultUser creatingUser, LocalDate creatingDate) {
        this.creatingUser = creatingUser;
        this.creatingDate = creatingDate;
    }

    public Product() {
        this.creatingUser = new DefaultUser();
        this.creatingDate = LocalDate.now();
    }

    public Product(String title, DefaultUser creatingUser) {
        this.title = title;
        this.creatingUser = creatingUser;
        this.creatingDate = LocalDate.now();
    }

    public void setProperties(ProductProperties properties) {
        long id = this.properties.getId();
        this.properties = properties;
        this.properties.setId(id);
    }

    public void setId(Long id) {
        this.id = id;
        this.properties.setId(id);
    }

    public Product(String title, DefaultUser creatingUser, ProductProperties properties) {
        this.title = title;
        this.creatingUser = creatingUser;
        this.properties = properties;
        this.creatingDate = LocalDate.now();
    }

    public Product(String title, String description, DefaultUser creatingUser, ProductProperties properties) {
        this.title = title;
        this.description = description;
        this.creatingUser = creatingUser;
        this.properties = properties;
        this.creatingDate = LocalDate.now();
    }

    public boolean isConfigured() {
        return !properties.hasEmptyProperties();
    }
}