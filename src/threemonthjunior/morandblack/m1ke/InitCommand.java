/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * A command to mark the application state as valid 
 * for all available commands processing. 
 * 
 * @author Dmytro Briukhatskyi
 */
class InitCommand extends AbstractCommand implements Command {

    /* (non-Javadoc)
     * @see threemonthjunior.morandblack.m1ke.Command#execute()
     */
    @Override
    public void execute(ApplicationState appState) {
        appState.initialize();
    }

}
