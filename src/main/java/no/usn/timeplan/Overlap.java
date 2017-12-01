package no.usn.timeplan;


import java.util.ArrayList;
import java.util.List;

public class Overlap {

    private Session sessionA;
    private Session sessionB;

    public Overlap(Session sessionA, Session sessionB) {
        this.sessionA = sessionA;
        this.sessionB = sessionB;
    }

    public Session getSessionA() {
        return sessionA;
    }

    public void setSessionA(Session sessionA) {
        this.sessionA = sessionA;
    }

    public Session getSessionB() {
        return sessionB;
    }

    public void setSessionB(Session sessionB) {
        this.sessionB = sessionB;
    }

    public List<Session> getSessions() {
        // På dette tidspunktet begynte jeg å bli litt brisen
        List<Session> sessions = new ArrayList<>();
        sessions.add(sessionA);
        sessions.add(sessionB);
        return sessions;
    }
}
