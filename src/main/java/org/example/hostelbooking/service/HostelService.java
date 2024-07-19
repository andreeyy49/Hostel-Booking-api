package org.example.hostelbooking.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Hostel;
import org.example.hostelbooking.repository.HostelRepository;
import org.example.hostelbooking.repository.HostelSpecification;
import org.example.hostelbooking.utils.BeanUtils;
import org.example.hostelbooking.web.dto.hostel.HostelFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HostelService {

    private final HostelRepository hostelRepository;

    public List<Hostel> findAll() {
        return hostelRepository.findAll();
    }

    public List<Hostel> filterBy(HostelFilter filter) {
        return hostelRepository.findAll(HostelSpecification.withFilter(filter),
                PageRequest.of(
                        filter.getPageNumber(),
                        filter.getPageSize()
                )).getContent();
    }

    public Hostel findById(Long id) {
        return hostelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                "Hostel with ID: {0} not found", id
        )));
    }

    public Hostel save(Hostel hostel) {
        return hostelRepository.save(hostel);
    }

    public Hostel update(Hostel hostel) {
        Hostel existHostel = findById(hostel.getId());

        BeanUtils.copyNotNullProperties(hostel, existHostel);

        return hostelRepository.save(hostel);
    }

    public void delete(Long id) {
        hostelRepository.deleteById(id);
    }

    public Hostel updateRating(Long id, Float newMark) {
        Hostel hostel = findById(id);

        Float rating = hostel.getRating();
        Float numberOfRating = hostel.getRatingCounter();

        if(rating != null && numberOfRating != null) {
            float totalRating = rating * numberOfRating;
            totalRating = totalRating - rating + newMark;
            rating = totalRating/numberOfRating;
            rating = (float) Math.round(rating * 10);
            rating /= 10;
            numberOfRating++;

            hostel.setRating(rating);
            hostel.setRatingCounter(numberOfRating);

            return update(hostel);
        }

        rating = newMark;
        rating = (float) (Math.round(rating * 10) / 10);
        numberOfRating = 1F;

        hostel.setRating(rating);
        hostel.setRatingCounter(numberOfRating);

        return update(hostel);
    }
}
