/**
 * Command-line interface class
 */
package threemonthjunior.morandblack.m1ke;

import java.util.Scanner;

/**
 * A helper class for the console operations.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
final class Console {
    
    /** Scans console input */
    private static Scanner userInput = new Scanner(System.in); 
    
    /** Noninstantiable */
    private Console() {}

    /**
     * Gets the next typed command followed by a line feed.
     * 
     * @return String array with command and it's arguments, 
     *         each in separate array entry.
     */
    public static String getInput() {
        return userInput.nextLine();
    }
    
    /**
     * Prints provided message to the console.
     * 
     * @param message
     *        message to print
     */
    public static void print(String message) {
        System.out.println(message);
    }
    
    /**
     * Gets a confirmation to an action from the user.
     * 
     * @param message
     *        a message to display in the form of 
     *        "All your data will be lost. Do you want to proceed?".
     *        there will be automatically added a suffix 
     *        containing user input options.
     * 
     * @return true if user confirmed the action.
     */
    public static boolean confirmAction(String message) {
        print(message + " (yes/no)");
        
        if ("yes".equals(getInput()))
            return true;
        
        return false;
    }
    
}
