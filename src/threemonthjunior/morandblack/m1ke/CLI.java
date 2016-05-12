/**
 * Command-line interface class
 */
package threemonthjunior.morandblack.m1ke;

import java.util.Scanner;

/**
 * @author Dmytro Briukhatskyi
 *
 */
final class CLI {
    
    /** Scans console input */
    private static Scanner userInput = new Scanner(System.in); 
    
    /** Noninstantiable */
    private CLI() {}

    /**
     * Gets the next typed command followed by a line feed.
     * 
     * @return String array with command and it's arguments, 
     *         each in separate array entry.
     */
    public static String[] getInput() {
        return userInput.nextLine().split("\\s");
    }
    
}
