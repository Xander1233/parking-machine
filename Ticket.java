public class Ticket {

    private Time arrivalTime;

    private boolean isPaid;

    Ticket(Time arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Time getArrivalTime() {
        return this.arrivalTime;
    }

    public void markAsPayed() {
        this.isPaid = true;
    }

    public boolean isPaid() {
        return isPaid;
    }
}
