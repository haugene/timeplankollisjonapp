package no.usn.timeplan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HovedProgram {

    public final static String FILE_PATH = "/git/timeplankollisjonapp/src/main/resources/timeplan.csv";

    public static void main(String[] args) {

        List<Session> sessions = new CSVLeser(FILE_PATH).readSessions();
        Set<String> teachers = new HashSet<>();
        Set<String> rooms = new HashSet<>();

        for (Session session : sessions)
        {
            rooms.add(session.getRoom());
            teachers.add(session.getTeacher());
        }

        List<Overlap> teacherOverlaps = new ArrayList<>();
        for (String teacher : teachers) {
            List<Session> sessionsForTeacher = findSessionsForTeacher(teacher, sessions);
            teacherOverlaps.addAll(new RuleChecker().findOverlaps(sessionsForTeacher));
        }

        List<Overlap> roomOverlaps = new ArrayList<>();
        for (String room : rooms) {
            List<Session> sessionsInRoom = findSessionsInRoom(room, sessions);
            roomOverlaps.addAll(new RuleChecker().findOverlaps(sessionsInRoom));
        }

        System.out.println("OVERLAPPER PÅ LÆRER:");
        for (Overlap overlap : teacherOverlaps) {
            if (!overlap.getSessionA().getTeacher().contains("?")){
                System.out.println(overlap.getSessionA().toString() + " overlapper med " + overlap.getSessionB().toString());
            }
        }

        System.out.println("OVERLAPPER PÅ ROM:");
        for (Overlap overlap : roomOverlaps) {
            if (!overlap.getSessionA().getRoom().isEmpty() && !overlap.getSessionA().getRoom().equals("Ute")) {
                System.out.println(overlap.getSessionA().toString() + " overlapper med " + overlap.getSessionB().toString());
            }
        }

        System.out.println("Totalt antall økter: " + sessions.size());
        System.out.println("Totalt antall overlappende økter: " + teacherOverlaps.size());


    }

    private static List<Session> findSessionsForTeacher(String teacher, List<Session> sessions) {
        return sessions.stream().filter(session -> teacher.equals(session.getTeacher())).collect(Collectors.toList());
    }

    private static List<Session> findSessionsInRoom(String room, List<Session> sessions) {
        return sessions.stream().filter(session -> room.equals(session.getRoom())).collect(Collectors.toList());
    }
}
