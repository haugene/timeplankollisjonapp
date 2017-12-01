package no.usn.timeplan;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVLeser
{

    private final String TIMESLOT_REGEX = "\\d\\d:\\d\\d-\\d\\d:\\d\\d";
    private String filePath;

    public CSVLeser(String filePath) {
        this.filePath = filePath;
    }

    public List<Session> readSessions() {
        List<Session> sessions = new ArrayList<Session>();
        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] line;
            while ((line = reader.readNext()) != null) {
                String[] columns = line[0].split(";");

                if(isSession(columns)) {
                    sessions.add(new Session(columns));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    private boolean isSession(String[] columns) {
        return columns.length > 10 && columns[8].matches(TIMESLOT_REGEX);
    }
}
