package company.Observer;

public class Person {
    private String name;
    private int ticketNumber;
    private String arrivel;

    public Person(String name, int ticketNumber, String arrivel) {
        this.name = name;
        this.ticketNumber = ticketNumber;
        this.arrivel = arrivel;
    }

    public String getName() {
        return name;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public String getArrivel() {
        return arrivel;
    }

    public Person setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
        return this;
    }

    public Person setArrivel(String arrivel) {
        this.arrivel = arrivel;
        return this;
    }

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", ticketNumber=" + ticketNumber +
                ", arrivel='" + arrivel + '\'' +
                '}';
    }
}
