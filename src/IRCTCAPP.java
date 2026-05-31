import java.util.*;

public class IRCTCAPP {

    private final Scanner sc = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        new IRCTCAPP().start();
    }

    public void start() {
        System.out.println("---- Welcome To IRCTC APP ----");
        while (true) {
            

            if (!userService.isLoggedIn()) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter Choice: ");

                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> exitApp();
                    default -> System.out.println("Invalid Choice");
                }
            } else {
                showUserMenu();
            }
        }
    }

    public void register() {
        System.out.print("Enter username: ");
        String username = sc.next();

        System.out.print("Enter password: ");
        String password = sc.next();

        sc.nextLine(); // consume leftover newline

        System.out.print("Enter fullname: ");
        String fullname = sc.nextLine();

        System.out.print("Enter Contact: ");
        String contact = sc.next();

        userService.registerUser(username, password, fullname, contact);
    }

    public void login() {
        System.out.print("Enter username: ");
        String username = sc.next();

        System.out.print("Enter password: ");
        String password = sc.next();

        if (userService.loginUser(username, password)) {
            showUserMenu();
        } else {
            System.out.println("Invalid Username or Password");
        }
    }

    private void showUserMenu() {

        while (userService.isLoggedIn()) {

            System.out.println("\n---- User Menu ----");
            System.out.println("1. Search Train");
            System.out.println("2. Book Ticket");
            System.out.println("3. View My Tickets");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. View All Trains");
            System.out.println("6. Logout");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> searchTrain();
                case 2 -> bookTicket();
                case 3 -> viewMyTicket();
                case 4 -> cancelTicket();
                case 5 -> bookingService.listAllTrains();
                case 6 -> userService.logOutUser();
                default -> System.out.println("Invalid Choice");
            }
        }
    }

    private void searchTrain() {

        System.out.print("Enter source station: ");
        String source = sc.next();

        System.out.print("Enter destination station: ");
        String destination = sc.next();

        List<Train> trains = bookingService.searchTrain(source, destination);

        if (trains.isEmpty()) {
            System.out.println("No train found between " + source + " and " + destination);
            return;
        }

        System.out.println("Trains Found:");

        for (Train train : trains) {
            System.out.println(train);
        }

        System.out.print("Do you want to book ticket (yes/no): ");
        String choice = sc.next();

        if (choice.equalsIgnoreCase("yes")) {

            System.out.print("Enter Train ID: ");
            int trainid = sc.nextInt();

            System.out.print("Enter number of seats to book: ");
            int seats = sc.nextInt();

            Ticket ticket = bookingService.bookTicket(
                    userService.getCurrentUser(),
                    trainid,
                    seats);

            if (ticket != null) {
                System.out.println("Booking Successful!");
                System.out.println(ticket);
            }
        } else {
            System.out.println("Returning to User Menu...");
        }
    }

    private void bookTicket() {

        System.out.print("Enter source station: ");
        String source = sc.next();

        System.out.print("Enter destination station: ");
        String destination = sc.next();

        List<Train> trains = bookingService.searchTrain(source, destination);

        if (trains.isEmpty()) {
            System.out.println("No train found between " + source + " and " + destination);
            return;
        }

        System.out.println("Trains Available:");

        for (Train train : trains) {
            System.out.println(train);
        }

        System.out.print("Enter Train ID: ");
        int trainid = sc.nextInt();

        System.out.print("Enter number of seats to book: ");
        int seats = sc.nextInt();

        Ticket ticket = bookingService.bookTicket(
                userService.getCurrentUser(),
                trainid,
                seats);

        if (ticket != null) {
            System.out.println("Booking Successful!");
            System.out.println(ticket);
        }
    }

    private void viewMyTicket() {

        List<Ticket> ticketByUser =
                bookingService.getTicketByUser(userService.getCurrentUser());

        if (ticketByUser.isEmpty()) {
            System.out.println("No tickets found!");
        } else {
            System.out.println("Your Tickets:");

            for (Ticket ticket : ticketByUser) {
                System.out.println(ticket);
            }
        }
    }

    private void cancelTicket() {

        System.out.print("Enter Ticket ID to cancel: ");
        int ticketid = sc.nextInt();

        bookingService.cancelTicket(
                ticketid,
                userService.getCurrentUser());
    }

    private void exitApp() {
        System.out.println("Thank You For Using IRCTC App.");
        System.exit(0);
    }
}