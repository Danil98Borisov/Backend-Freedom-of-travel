package nc.project.controller;

import nc.project.models.Room;
import nc.project.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/apartments")
    public Iterable<Room> findAll() {

        return roomRepository.findAll();
    }

    @PutMapping("/apartments/edit")
    Room editRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }
}