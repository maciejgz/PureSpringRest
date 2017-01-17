package pl.mg.doorsgame.repository;

import java.util.List;

import pl.mg.doorsgame.model.Door;

public interface DoorRepository {

    public static final int DOORS_NUMBER = 3;

    public List<Door> generateDoors();
    
}
