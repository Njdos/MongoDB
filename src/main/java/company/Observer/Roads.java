package company.Observer;

import java.time.LocalDate;

public class Roads {
    private String road;
    public int large;
    public LocalDate localDate;

    @Override
    public String toString() {
        return "Roads{" +
                "road='" + road + '\'' +
                ", large=" + large +
                ", localDate=" + localDate +
                '}';
    }

    public String getRoad() {
        return road;
    }

    public Roads(String road) {
        this.road = road;
    }

    public Roads setRoad(String road) {
        this.road = road;
        return this;
    }

    public int getLarge() {
        return large;
    }

    public Roads setLarge(int large) {
        this.large = large;
        return this;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public Roads setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
        return this;
    }

    public Roads(String road, int large, LocalDate localDate) {
        this.road = road;
        this.large = large;
        this.localDate = localDate;
    }
    public Roads() {}
}

