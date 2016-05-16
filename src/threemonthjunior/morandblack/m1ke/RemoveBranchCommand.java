/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * A command to remove existing branch.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
class RemoveBranchCommand extends AbstractCommand implements Command {

    /* (non-Javadoc)
     * @see threemonthjunior.morandblack.m1ke.Command#execute()
     */
    @Override
    public void execute(ApplicationState appState) {
        if (!validateStateAndRepo(appState))
            return;
        
        if (parametersEmpty()) 
            Console.print("Please supply branch name!");
        
        String branchName = parameters[0];
        
        if (branchName.equals(appState.getCurrentRepo().getActiveBranch())) {
            Console.print("Cannot remove active branch! "
                    + "Please, change active branch first.");
            return;
        }
        
        appState.getCurrentRepo().removeBranch(branchName);
        
        if (Settings.DEBUG_MODE)
            Console.print("remove branch '" + branchName + "': SUCCESS");
    }

}
