import java.util.*;

class SeatManager {

    TreeSet<Integer> seats;

    public SeatManager(int n) {
        seats = new TreeSet<>();
        for(int i=1;i<=n;i++){
            seats.add(i);
        }
    }

    public int reserve() {
        int seat = seats.first();
        seats.remove(seat);
        return seat;
    }

    public void unreserve(int seatNumber) {
        seats.add(seatNumber);
    }
}