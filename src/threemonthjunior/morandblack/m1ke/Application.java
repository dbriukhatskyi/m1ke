/**
 * The main application class.
 */
package threemonthjunior.morandblack.m1ke;

/**
 * @author Dmytro Briukhatsky
 *
 */
public class Application {

    /**
     * Entry-point method.
     * 
     * @param args
     */
    public static void main(String[] args) {
        ApplicationState appState = new ApplicationState();
        
        Command command = CommandLineDispatcher.getCommand(args);
        
        while (command.allowsToProceed()) {
            command.execute(appState);
            
            String[] nextInput = CLI.getInput();
            command = CommandLineDispatcher.getCommand(nextInput);
        }
    }

}
