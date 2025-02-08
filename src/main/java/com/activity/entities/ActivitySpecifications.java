package com.activity.entities;

import com.activity.dtos.ActivityRequestPaginationDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ActivitySpecifications {
    public static Specification<Activity> filtrarPorCriterios(ActivityRequestPaginationDTO filtro) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filtro.getStatus() != null && !filtro.getStatus().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("status"), "%" + filtro.getStatus().toUpperCase() + "%"));
            }
//            if (filtro.getPrecioMinimo() != null) {
//                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("precio"), filtro.getPrecioMinimo()));
//            }
//            if (filtro.getPrecioMaximo() != null) {
//                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("precio"), filtro.getPrecioMaximo()));
//            }
            if (filtro.getFullName() != null && !filtro.getFullName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("fullName"), filtro.getFullName().toUpperCase()));
            }
            if (filtro.getClientFullName() != null && !filtro.getClientFullName().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("clientFullName"), filtro.getClientFullName().toUpperCase()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
