/**
 * The main application class.
 */
package threemonthjunior.morandblack.m1ke;

/**
 * @author Dmytro Briukhatskyi
 *
 */
public class Application {
    
    private static ApplicationState appState;
    
    private static void loadAppState() {
        // TODO dummy implementation
        appState = new ApplicationState();
    }
    
    private static void saveAppState() {
        // TODO dummy implementation
        
        if (appState.isInitialized()) {
            // write current state to disk
        }
        
        if (appState.quitRequested()) {
            // remove the file with the request
        }
    }

    /**
     * Entry-point method.
     * 
     * @param args 
     *        command-line parameters
     */
    public static void main(String[] args) {
        loadAppState();
        
        Command command = CommandLineDispatcher.getCommand(args);
        
        command.execute(appState);
        
        saveAppState();
    }

}
