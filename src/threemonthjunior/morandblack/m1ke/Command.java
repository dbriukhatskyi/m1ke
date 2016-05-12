/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * @author Dmytro Briukhatsky
 *
 */
interface Command {
    void execute(ApplicationState appState); 
    void configure(String[] parameters); 
    boolean allowsToProceed();
}
