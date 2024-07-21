package org.example.hostelbooking.repository;

import org.example.hostelbooking.entity.Booking;
import org.example.hostelbooking.entity.Hostel;
import org.example.hostelbooking.entity.Room;
import org.example.hostelbooking.web.dto.room.RoomFilter;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public interface RoomSpecification {

    static Specification<Room> withFilter(RoomFilter filter) {
        return Specification.where(byId(filter.getId()))
                .and(byName(filter.getName()))
                .and(byMaxPeopleSize(filter.getMaxPeopleSize()))
                .and(byHostelId(filter.getHostelId()))
                .and(byPriceRange(filter.getMinPrice(), filter.getMaxPrice()))
                .and(byBootingTime(filter.getInBooking(), filter.getOutBooking()));
    }

    static Specification<Room> byId(Long id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get(Room.Fields.id), id);
        };
    }

    static Specification<Room> byName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get(Room.Fields.name), name);
        };
    }

    static Specification<Room> byMaxPeopleSize(Integer maxPeopleSize) {
        return (root, query, criteriaBuilder) -> {
            if (maxPeopleSize == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get(Room.Fields.maxPeopleSize), maxPeopleSize);
        };
    }

    static Specification<Room> byHostelId(Long hostelId) {
        return (root, query, criteriaBuilder) -> {
            if (hostelId == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("hostel").get(Hostel.Fields.id), hostelId);
        };
    }

    static Specification<Room> byPriceRange(Float minPrice, Float maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return null;
            }

            if (minPrice == null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get(Room.Fields.price), maxPrice);
            }

            if (maxPrice == null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(Room.Fields.price), minPrice);
            }

            return criteriaBuilder.between(root.get(Room.Fields.price), minPrice, maxPrice);
        };
    }

    static Specification<Room> byBootingTime(Instant inBooking, Instant outBooking) {
        Specification<Room> inBookingSpec = byInBootingTime(inBooking);
        Specification<Room> outBookingSpec = byOutBootingTime(outBooking);

        return inBookingSpec.and(outBookingSpec);
    }

    static Specification<Room> byInBootingTime(Instant inBooking) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("bookings").get(Booking.Fields.outBooking), inBooking);
    }

    static Specification<Room> byOutBootingTime(Instant outBooking) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get("bookings").get(Booking.Fields.inBooking), outBooking);
    }
}


