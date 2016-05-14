/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * @author Dmytro Briukhatskyi
 *
 */
interface Command {
    void execute(ApplicationState appState); 
    void configure(String[] parameters);
}
