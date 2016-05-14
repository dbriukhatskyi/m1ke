/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * A command to mark the application state as no longer needed.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
class QuitCommand extends AbstractCommand implements Command {

    /* (non-Javadoc)
     * @see threemonthjunior.morandblack.m1ke.Command#execute()
     */
    @Override
    public void execute(ApplicationState appState) {
        appState.requestQuit();
    }

}
