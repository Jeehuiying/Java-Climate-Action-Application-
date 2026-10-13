//Contributed by Leong Jao Yuan (104742) 
  
 
import java.io.IOException;

// Custom exception for file loading/writing errors in the leaderboard system.
// Extends IOException so it can be thrown and caught alongside standard IO errors.
public class FileLoadException extends IOException {
    // Name of the file that caused the error (may be null if not set)
    private String fileName;
    // Numeric error code to categorize the type of failure (default 0 if not set)
    private int errorCode;
    // Exact time (in milliseconds) when the exception was created
    private long timestamp;

    // Constructor for a simple error message (e.g. file not found)
    public FileLoadException(String message) {
        super(message);
        this.timestamp = System.currentTimeMillis();
    }

    // Constructor for wrapping another exception as the root cause
    public FileLoadException(String message, Throwable cause) {
        super(message, cause);
        this.timestamp = System.currentTimeMillis();
    }

    public String getFileName() {
        return fileName;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
