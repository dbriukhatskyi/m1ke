/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * An abstract implementation of the {@code Command} interface to implement 
 * a dummy configure() method for the commands that don't need configuration.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
abstract class AbstractCommand implements Command {
    
    protected String[] parameters;

    /* (non-Javadoc)
     * @see threemonthjunior.morandblack.m1ke.Command#configure(java.lang.String[])
     */
    @Override
    public void configure(String[] parameters) {
        if (parameters != null) {
            this.parameters = new String[parameters.length];
            System.arraycopy(parameters, 0, this.parameters, 0, parameters.length);
        }
    }
    
    /**
     * Checks if the appState provided is a valid application state 
     * and prints a message to console if it isn't. 
     * 
     * @param appState
     *        {@code ApplicationState} instance to validate
     * 
     * @return true if appState is a valid application state,
     *         false otherwise
     */
    protected boolean validateState(ApplicationState appState) {
        if (!appState.isInitialized()) {
            Console.print("m1ke not initialized! Use m1ke init to do it.");
            return false;
        }
        
        return true;
    }
    
    /**
     * Checks if the appState provided is a valid application state and 
     * has a valid repository reference and prints a message to console 
     * if at least one of this conditions is not satisfied. 
     * 
     * @param appState
     *        {@code ApplicationState} instance to validate
     * 
     * @return true if appState is a valid application state 
     *         and has a valid repository reference,
     *         false otherwise
     */
    protected boolean validateStateAndRepo(ApplicationState appState) {
        if (!validateState(appState)) 
            return false;
        
        if (appState.getCurrentRepo() == null) {
            Console.print("No working directory selected! "
                    + "Use m1ke integrate <path> to do it.");
            return false;
        }
        
        return true;
    }
    
    /**
     * Checks if there were any parameters supplied to 
     * {@link #configure(String[])} method.
     * 
     * @return true if non-empty parameters array was supplied,
     *         false otherwise 
     */
    protected boolean parametersEmpty() {
        return parameters == null || parameters.length < 1;
    }
    
}
