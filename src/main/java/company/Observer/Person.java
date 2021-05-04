package company.Observer;

public class Person {
    private String name;
    private int ticketNumber;
    private String arrivel;
    private String platform;

    public Person(String name, int ticketNumber, String arrivel, String platform) {
        this.name = name;
        this.ticketNumber = ticketNumber;
        this.arrivel = arrivel;
        this.platform = platform;
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

    public String getPlatform() {
        return platform;
    }

    public Person setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
        return this;
    }

    public Person setArrivel(String arrivel) {
        this.arrivel = arrivel;
        return this;
    }

    public Person setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public Person(String name) {
        this.name = name;
    }


}
