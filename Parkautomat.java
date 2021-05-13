public class Parkautomat {

    private Time currentTime;

    private double parkingTax;

    private Time parkingDuration;

    private Ticket ticket;

    private double pricePerHour = 1.5;

    private double priceInTheFirstHour = 1.5;

    Parkautomat(Time currentTime, double pricePerHour, double priceInTheFirstHour) {
        this.currentTime = currentTime;
        this.pricePerHour = pricePerHour;
        this.priceInTheFirstHour = priceInTheFirstHour;
    }

    Parkautomat(Time currentTime, double pricePerHour) {
        this.currentTime = currentTime;
        this.pricePerHour = pricePerHour;
    }

    Parkautomat(Time currentTime) {
        this.currentTime = currentTime;
    }

    public void insertTicket(Ticket ticket) {
        this.ticket = ticket;

        this.calculateParkingDuration();
        this.calculateParkingTaxes();
        this.showTaxes();
    }

    private void calculateParkingDuration() {
        Time ticketTime = this.ticket.getArrivalTime();

        this.parkingDuration = new Time(Math.abs(this.currentTime.getMinute() - ticketTime.getMinute()), Math.abs(this.currentTime.getHour() - ticketTime.getHour()));

        return;
    }

    private void calculateParkingTaxes() {
        int ticketHours = (this.parkingDuration.getHour());

        if (ticketHours == 0 && this.priceInTheFirstHour < 0) {
            this.parkingTax = 2;
            return;
        }

        this.parkingTax = ticketHours * pricePerHour + priceInTheFirstHour;

        return;
    }

    private void showTaxes() {
        System.out.println("---------------------------");
        System.out.printf("%15s: %5s Uhr\n", "Arrival time", this.ticket.getArrivalTime().toString());
        System.out.printf("%15s: %5s Uhr\n", "Current time", this.currentTime.toString());
        System.out.printf("%15s: %5s h\n", "Parking duration", this.parkingDuration.toString());
        System.out.printf("%15s: %.2f €\n", "Parking bill", this.floatingPointRoundFix(this.parkingTax));
        System.out.println("---------------------------");
        return;
    }

    public void insertTaxes(double betrag) {
        if (betrag < this.parkingTax && this.parkingTax > 0) {
            this.parkingTax -= betrag;
            System.out.println("This isn't enough. " + this.floatingPointRoundFix(this.parkingTax) + " € are still left!");
            return;
        }

        this.ticket.isPaid();
        System.out.println("The ticket got paid! We thank you for your stay." + (betrag > this.parkingTax ? " Your change, "
                + floatingPointRoundFix((betrag - this.parkingTax)) + " €, will be paid out to you shortly!" : ""));
        this.ticket = null;
        this.parkingTax = 0;
        this.parkingDuration = null;
        this.currentTime = null;
        return;
    }

    private double floatingPointRoundFix(double n) {
        double temp = n * 100;
        temp = Math.floor(temp);
        return temp / 100;
    }

}
