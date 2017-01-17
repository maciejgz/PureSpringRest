package pl.mg.doorsgame.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import pl.mg.doorsgame.model.Door;
import pl.mg.doorsgame.model.DoorStatus;
import pl.mg.doorsgame.model.Prize;

/**
 * 
 * @author Maciej Gzik
 *
 */
@Repository
public class InMemoryDoorRepository implements DoorRepository {

    @Override
    public List<Door> generateDoors() {
        List<Door> doors = new ArrayList<>();
        for (int i = 0; i < DoorRepository.DOORS_NUMBER; i++) {
            Door door = new Door(i);
            door.setPrize(Prize.randomPrize());
            door.setStatus(DoorStatus.CLOSED);
            doors.add(door);
        }
        return doors;
    }

}
