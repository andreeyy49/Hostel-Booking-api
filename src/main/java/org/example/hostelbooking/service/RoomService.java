package org.example.hostelbooking.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.hostelbooking.entity.Room;
import org.example.hostelbooking.repository.RoomRepository;
import org.example.hostelbooking.utils.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Hostel with ID: {0} not found", id)));
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public Room update(Room room) {
        Room existRoom = findById(room.getId());

        BeanUtils.copyNotNullProperties(room, existRoom);

        return roomRepository.save(room);
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}