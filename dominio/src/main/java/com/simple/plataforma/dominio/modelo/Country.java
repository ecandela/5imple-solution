package com.simple.plataforma.dominio.modelo;


import java.util.Calendar;
import javax.persistence.*;
/**
 * Created by ecandela on 30/08/2015.
 */
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "countries")
@NamedQuery(name = "Country.getByPopulationNamedQuery", query = "FROM Country WHERE population = ?1")
public class Country extends AuditableEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique=true)
    private String name;

    @Column(nullable = false)
    private Integer population;

    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar creation;

    public Country()
    {
        super();
    }

    public Country(String name, Integer population)
    {
        super();
        this.name = name;
        this.population = population;
    }

    @PrePersist
    public void onPersist()
    {
        creation = Calendar.getInstance();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getPopulation()
    {
        return population;
    }

    public void setPopulation(Integer population)
    {
        this.population = population;
    }

    public Calendar getCreation()
    {
        return creation;
    }

    public void setCreation(Calendar creation)
    {
        this.creation = creation;
    }

}