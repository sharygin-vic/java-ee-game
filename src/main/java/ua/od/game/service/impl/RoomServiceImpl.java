package ua.od.game.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.game.dto.RoomDto;
import ua.od.game.exception.ResourceNotFoundException;
import ua.od.game.model.RoomEntity;
import ua.od.game.repository.RoomRepository;
import ua.od.game.service.RoomService;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ruslan.gramatic on 6/27/18.
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    public RoomRepository roomRepository;

    public List<RoomDto> getListOfRooms() {
        final List<RoomDto> rooms = new LinkedList<>();
        roomRepository.findAll().forEach((RoomEntity roomEntity) -> {
            rooms.add(new RoomDto(){{
                setId(roomEntity.getId());
                setName(roomEntity.getName());
                setDescription(roomEntity.getDescription());
            }});
        });
        return rooms;
    }

    public RoomDto joinRoom(Long roomId) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));

        room = roomRepository.save(room);

        return new RoomDto(
                room.getId(),
                room.getName(),
                room.getDescription(),
                room.getStartGameTime()
        );
    }

    public RoomDto leaveRoom(Long roomId) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room", "id", roomId));

        room = roomRepository.save(room);

        return new RoomDto(
            room.getId(),
            room.getName(),
            room.getDescription(),
            room.getStartGameTime()
        );
    }
}
