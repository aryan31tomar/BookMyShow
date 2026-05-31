public class Ticket {
    private int ticketid;
    private User user;
    private Train train;
    private int seatbooked;
    private static int counter = 1001;

    Ticket(User user, Train train, int seatbooked) {
        this.ticketid = counter++;
        this.user = user;
        this.train = train;
        this.seatbooked = seatbooked;
    }

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getSeatbooked() {
        return seatbooked;
    }

    public void setSeatbooked(int seatbooked) {
        this.seatbooked = seatbooked;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Ticket.counter = counter;
    }

    @Override
    public String toString() {
        return "Ticket Id: " + ticketid +
               " | Train: " + train.getName() +
               " | Route: " + train.getsource() + " -> " + train.getDestination() +
               " | Seats: " + seatbooked +
               " | Booked By: " + user.getfullname();
    }
}