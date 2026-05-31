import java.util.*;

public class BookingService {

    private List<Train> trainList = new ArrayList<>();
    private List<Ticket> ticketList = new ArrayList<>();

    public BookingService() {
        trainList.add(new Train(101, "Rajdhani Express", "Delhi", "Nagpur", 100));
        trainList.add(new Train(102, "Shatabdi Express", "Delhi", "Mumbai", 60));
        trainList.add(new Train(103, "Vande Bharat Express", "Agra", "Delhi", 70));
        trainList.add(new Train(104, "Tejas Express", "Delhi", "Goa", 90));
        trainList.add(new Train(105, "Meerut Express", "Meerut", "Manali", 20));
    }

    public List<Train> searchTrain(String source, String destination) {
        List<Train> res = new ArrayList<>();

        for (Train train : trainList) {
            if (train.getsource().equalsIgnoreCase(source)
                    && train.getDestination().equalsIgnoreCase(destination)) {
                res.add(train);
            }
        }
        return res;
    }

    public Ticket bookTicket(User user, int trainid, int seatcount) {

        for (Train train : trainList) {

            if (train.getTrainid() == trainid) {

                if (train.bookSeats(seatcount)) {

                    Ticket ticket = new Ticket(user, train, seatcount);
                    ticketList.add(ticket);
                    return ticket;

                } else {
                    System.out.println("Not enough seats available");
                    return null;
                }
            }
        }

        System.out.println("Train id not found");
        return null;
    }

    public List<Ticket> getTicketByUser(User user) {

        List<Ticket> res = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            if (ticket.getUser().getUserName()
                    .equalsIgnoreCase(user.getUserName())) {
                res.add(ticket);
            }
        }

        return res;
    }

    public boolean cancelTicket(int ticketid, User user) {

        Iterator<Ticket> it = ticketList.iterator();

        while (it.hasNext()) {

            Ticket ticket = it.next();

            if (ticket.getTicketid() == ticketid
                    && ticket.getUser().getUserName()
                            .equalsIgnoreCase(user.getUserName())) {

                Train train = ticket.getTrain();
                train.cancelSeats(ticket.getSeatbooked());

                it.remove();

                System.out.println("Ticket " + ticketid
                        + " Cancelled Successfully!!!");

                return true;
            }
        }

        System.out.println("Ticket not found");
        return false;
    }

    public void listAllTrains() {

        System.out.println("List Of All Trains:");

        for (Train train : trainList) {
            System.out.println(train);
        }
    }
}