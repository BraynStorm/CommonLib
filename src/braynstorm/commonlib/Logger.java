package braynstorm.commonlib;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Logger {
    private static boolean I = false;
    
    public static void init(String mainFolder){
        if(I)
            return;
        
        Ref.mainDir = mainFolder;
        Common.createFolder(Ref.mainDir + "/logs");
        String logPath = Ref.mainDir + "/logs/" + (System.currentTimeMillis() / 1000L) + ".log";
        
        try {
            System.setErr(new PrintStream(logPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        I = true;
    }

    
    public static void logExceptionInfo(Exception e) {
        logException(LogLevel.INFO, e);
    }
    
    public static void logExceptionDebug(Exception e) {
        logException(LogLevel.DEBUG, e);
    }
    
    public static void logExceptionWarning(Exception e) {
        logException(LogLevel.WARNING, e);
    }
    
    public static void logExceptionCritical(Exception e) {
        logException(LogLevel.CRITICAL, e);
    }
    
    public static void logExceptionImpossibru(Exception e) {
        logException(LogLevel.IMPOSSIBRU, e);
    }
    
    public static void logException(LogLevel level, Exception e) {
        e.printStackTrace();
        
        if(level.equals(LogLevel.IMPOSSIBRU) || level.equals(LogLevel.CRITICAL)){
            e.printStackTrace(System.out);
            System.exit(1);
        } else if (level.equals(LogLevel.DEBUG))
            e.printStackTrace(System.out);
    }
    
    public static void logInfo(String message){
        log(LogLevel.INFO, message);
    }
    
    public static void logInfo(byte[] data){
        StringBuilder sb = new StringBuilder(data.length * 3);
        
        for(int i = 0; i < data.length; i++){
            sb.append(Integer.toHexString(data[i])).append(' ');
            
            if(i % 8 == 0)
                sb.append('\n');
        }
        log(LogLevel.INFO, sb.toString());
    }
    
    public static void logDebug(String message){
        log(LogLevel.DEBUG, message);
    }
    
    public static void logWarning(String message){
        log(LogLevel.WARNING, message);
    }
    
    public static void logCritical(String message){
        log(LogLevel.CRITICAL, message);
    }
    
    public static void logImpossibru(String message){
        log(LogLevel.IMPOSSIBRU, message);
    }
    
    public static void log(LogLevel level, String message){
        String toWrite = "[" + level.toString() + "] " + message;
        System.err.println(toWrite);
        
        if (level == LogLevel.IMPOSSIBRU || level == LogLevel.CRITICAL){
            System.out.println(toWrite);
            System.exit(1);
        } else if (level == LogLevel.DEBUG)
            System.out.println(toWrite);
    }
    
    public enum LogLevel{
        IMPOSSIBRU,
        CRITICAL,
        WARNING,
        INFO,
        DEBUG;
    }
}
