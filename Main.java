import java.time.LocalDateTime;
import java.util.Scanner;

import static java.lang.Double.parseDouble;

public class Main {
    public static void main(String[] args) {

        LocalDateTime currentTime = LocalDateTime.now();

        Ticket ticket = new Ticket(new Time(currentTime.getMinute(), currentTime.getHour()));

        Parkautomat parkautomat = new Parkautomat(new Time(currentTime.getMinute() + 30, currentTime.getHour() + 3), 1.5, 2);

        parkautomat.insertTicket(ticket);

        Scanner sc = new Scanner(System.in);

        do {
            double tax;

            do {
                System.out.print("Please insert money to pay the taxes () > ");
                tax = matchGebuehr(sc.next());
            } while (tax < 0);

            parkautomat.insertTaxes(tax);
        } while (!ticket.isPaid());
    }

    public static double matchGebuehr(String tax) {
        if (tax.matches("[0-9]+.?[0-9]*")) return parseDouble(tax);
        return -1.0;
    }
}
