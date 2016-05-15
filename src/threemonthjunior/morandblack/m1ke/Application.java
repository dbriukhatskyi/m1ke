/**
 * The main application class.
 */
package threemonthjunior.morandblack.m1ke;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main application class of m1ke VCS.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
public class Application {
    
    /** Current application state */
    private static ApplicationState appState;
    
    /**
     * Loads application state from disk. 
     * If a state storage file is not found an empty appState object is used. 
     * 
     * @throws IOException
     *         if opening a state storage file has failed
     */
    private static void loadAppState() throws IOException {
        appState = new ApplicationState();
        Path storagePath = Paths.get(Settings.APPSTATE_FILE_NAME);
        
        if (!Files.exists(storagePath))
            return;
        
        try (InputStream stateInputStream = Files.newInputStream(storagePath); 
             Scanner stateScanner = new Scanner(stateInputStream, "UTF-8"))
        {
            if (stateScanner.hasNextLine()) {
                boolean initialized = Boolean.parseBoolean(stateScanner.nextLine());
                
                if (initialized)
                    appState.initialize();
            }

            if (stateScanner.hasNextLine()) {
                String workingDirStr = stateScanner.nextLine().trim();
                                
                if (!workingDirStr.isEmpty()) {
                    Path workingDirectory = Paths.get(workingDirStr);
                    appState.setWorkingDirectory(workingDirectory);
                }
            }
        }
    }
    
    /**
     * Saves current application state to disk.
     * If {@code appState.quitRequested()} returns {@code true} 
     * the state storage file is removed if exists.
     * 
     * @throws IOException
     */
    private static void saveAppState() throws IOException {
        Path storagePath = Paths.get(Settings.APPSTATE_FILE_NAME);
        
        if (appState.isInitialized() && !appState.quitRequested()) {
            try (Writer resultWriter = Files.newBufferedWriter(
                    storagePath, 
                    Charset.forName("UTF-8"))) 
            {
                String workingDirStr = "";
                
                if (appState.getWorkingDirectory() != null)
                    workingDirStr = appState.getWorkingDirectory().toString();
                
                resultWriter.append(String.valueOf(appState.isInitialized()))
                            .append(System.lineSeparator())
                            .append(workingDirStr)
                            .append(System.lineSeparator());
            }
        }
        
        if (appState.quitRequested()) {
            if (Files.exists(storagePath))
                Files.delete(storagePath);
        }
    }

    /**
     * Entry-point method.
     * 
     * @param args 
     *        command-line parameters
     */
    public static void main(String... args) {
        try {
            loadAppState();
        } catch (IOException e) {
            Console.print("ERROR: Failed to load application state! "
                    + "Unable to proceed.");
            return;
        }
        
        Command command = CommandLineDispatcher.getCommand(args);
        
        command.execute(appState);
        
        try {
            saveAppState();
        } catch (IOException e) {
            Console.print("ERROR: Failed to save application state!"
                    + System.lineSeparator()
                    + "Reinitialization is probably needed!");
            return;
        }
    }

}
