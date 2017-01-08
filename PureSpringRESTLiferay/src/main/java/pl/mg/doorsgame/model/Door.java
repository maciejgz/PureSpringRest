package pl.mg.doorsgame.model;

public class Door {

    private final int doorId;
    private Prize prize;
    private DoorStatus status;

    public Door(int id) {
        this.doorId = id;
    }

    public Prize getPrize() {
        return prize;
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public DoorStatus getStatus() {
        return status;
    }

    public void setStatus(DoorStatus status) {
        this.status = status;
    }

    public int getDoorId() {
        return doorId;
    }

}
