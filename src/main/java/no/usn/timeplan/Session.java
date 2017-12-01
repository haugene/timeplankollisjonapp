package no.usn.timeplan;

import org.joda.time.DateTime;

import static java.lang.Integer.valueOf;

public class Session {

    private String teacher;
    private String course;
    private String room;
    private DateTime start;
    private DateTime end;

    public Session(String[] columns) {
        this.course = columns[1];
        this.teacher = columns[9];
        this.room = columns[10];
        this.start = parseTime(columns[5], columns[8].split("-")[0]);
        this.end = parseTime(columns[5], columns[8].split("-")[1]);
    }

    public boolean overlaps(Session otherSession){
        return isNotSameSession(otherSession)
                && this.start.getMillis() <= otherSession.getEnd().getMillis()
                && this.end.getMillis() >= otherSession.getStart().getMillis();
    }

    private boolean isNotSameSession(Session otherSession) {
        return !this.equals(otherSession);
    }

    @Override
    public String toString() {
        return "Emne: " + course + ", Lærer: " + teacher + ", Rom: " + room + ", Start: " + start + ", Slutt: " + end;
    }

    private DateTime parseTime(String date, String time) {
        Integer day = valueOf(date.split("\\.")[0]);
        Integer month = getMonth(date.split("\\.")[1]);

        Integer hour = valueOf(time.split(":")[0]);
        Integer minute = valueOf(time.split(":")[1]);

        return new DateTime(2017, month, day, hour, minute);
    }

    private Integer getMonth(String monthString) {
        Integer month;

        switch (monthString) {
            case "jan":
                month = 01;
                break;
            case "feb":
                month = 02;
                break;
            case "mar":
                month = 03;
                break;
            case "apr":
                month = 04;
                break;
            case "mai":
                month = 05;
                break;

            default: throw new RuntimeException("Klarte ikke å tolke måned: " + monthString);
        }
        return month;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
