package no.usn.timeplan;

import java.util.ArrayList;
import java.util.List;

public class RuleChecker {

    public List<Overlap> findOverlaps(List<Session> sessions) {
        List<Overlap> overlaps = new ArrayList<>();
        for (Session session : sessions) {
            for (Session otherSession : sessions) {
                if (session.overlaps(otherSession)) {
                    overlaps.add(new Overlap(session, otherSession));
                }
            }
        }
        return overlaps;
    }
}
