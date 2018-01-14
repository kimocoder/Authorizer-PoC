package net.tjado.authorizer;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;



public class Log {

    // instance object (singleton)
    private static final Log INSTANCE = new Log();

    // private constructor -> singleton
    private Log() {/************** nothing in constructor **************/}

    public static Log getInstance() {
        return INSTANCE;
    }

    public class LogEntry {
        public String time;
        public Level level;
        public String message;
    }

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public enum Level { Debug };

    private List<LogEntry> logEntries = new ArrayList<LogEntry>();


    public List<LogEntry> getLogs() {
        return logEntries;
    }

    public String getLogsString() {
        return getLogsString(false);
    }

    public String getLogsString(boolean trim) {

        StringBuilder builder = new StringBuilder("");
        for (LogEntry entry : logEntries) {

            builder.append( String.format("%s [%s] %s%n", (trim) ? entry.time.substring(10) : entry.time, String.valueOf(entry.level).toUpperCase(), entry.message) );
        }

        return builder.toString();
    }

    // debug
    public void debug(String msg) {

        this.helper(Level.Debug, msg);
    }

    public void helper(Level level, String msg) {

        LogEntry entry = new LogEntry();
        entry.time = dateFormat.format(new Date());
        entry.level = level;
        entry.message = ToolBox.formatString(msg);

        logEntries.add( entry );
    }

}
