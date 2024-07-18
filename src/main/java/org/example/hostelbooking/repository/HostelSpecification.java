package org.example.hostelbooking.repository;

import org.example.hostelbooking.entity.Hostel;
import org.example.hostelbooking.web.entity.hostel.HostelFilter;
import org.springframework.data.jpa.domain.Specification;

public interface HostelSpecification {

    static Specification<Hostel> withFilter(HostelFilter filter) {
        return Specification.where(byHostelId(filter.getId()))
                .and(byHostelName(filter.getName()))
                .and(byHostelDistance(filter.getDistance()))
                .and(byHostelRating(filter.getRating()))
                .and(byHostelTitle(filter.getTitle()))
                .and(byHostelRatingCounter(filter.getRatingCounter()))
                .and(byHostelCity(filter.getCity()))
                .and(byHostelAddress(filter.getAddress()));
    }

    static Specification<Hostel> byHostelName(String name){
        return (root, query, criteriaBuilder) -> {
            if(name == null){
                return null;
            }

            return criteriaBuilder.equal(root.get(Hostel.Fields.name), name);
        };
    }

    static Specification<Hostel> byHostelId(Long id){
        return (root, query, criteriaBuilder) -> {
            if(id == null){
                return null;
            }

            return criteriaBuilder.equal(root.get(Hostel.Fields.id), id);
        };
    }

    static Specification<Hostel> byHostelTitle(String title){
        return (root, query, criteriaBuilder) -> {
            if(title == null){
                return null;
            }

            return criteriaBuilder.equal(root.get(Hostel.Fields.title), title);
        };
    }

    static Specification<Hostel> byHostelCity(String city){
        return (root, query, criteriaBuilder) -> {
            if(city == null){
                return null;
            }

            return criteriaBuilder.equal(root.get(Hostel.Fields.city), city);
        };
    }

    static Specification<Hostel> byHostelAddress(String address){
        return (root, query, criteriaBuilder) -> {
            if(address == null){
                return null;
            }

            return criteriaBuilder.equal(root.get(Hostel.Fields.address), address);
        };
    }

    static Specification<Hostel> byHostelDistance(Float distance){
        return (root, query, criteriaBuilder) -> {
            if(distance == null){
                return null;
            }

            return criteriaBuilder.equal(root.get(Hostel.Fields.distance), distance);
        };
    }

    static Specification<Hostel> byHostelRating(Float rating){
        return (root, query, criteriaBuilder) -> {
            if(rating == null){
                return null;
            }

            return criteriaBuilder.equal(root.get(Hostel.Fields.rating), rating);
        };
    }

    static Specification<Hostel> byHostelRatingCounter(Float ratingCounter){
        return (root, query, criteriaBuilder) -> {
            if(ratingCounter == null){
                return null;
            }

            return criteriaBuilder.equal(root.get(Hostel.Fields.ratingCounter), ratingCounter);
        };
    }

}
