package com.mindstorm.data.repository.specifications;

import com.mindstorm.data.entity.Country;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by ecandela on 05/09/2015.
 */
public abstract class CountrySpecifications
{
    public static Specification<Country> searchByName(final String name) {
        return new Specification<Country>() {

            @Override
            public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder)
            {
                return criteriaBuilder.equal(root.get("name"), name);
            }
        };
    }
}
