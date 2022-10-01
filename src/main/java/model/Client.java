package model;
public class Client {
    private int id;
    private int timeArrival;
    private int timeService;

    public Client(int id, int timeArrival, int timeService) {
        this.id = id;
        this.timeArrival = timeArrival;
        this.timeService = timeService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(int timeArrival) {
        this.timeArrival = timeArrival;
    }

    public int getTimeService() {
        return timeService;
    }

    public void setTimeService(int timeService) {
        this.timeService = timeService;
    }

    @Override
    public String toString() {
        return "(" + id + "," + timeArrival + "," + timeService + ")";
    }
}
