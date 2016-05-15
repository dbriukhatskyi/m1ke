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
        
        if (appState.getCurrentRepo().getActiveBranch().equals(branchName)) {
            Console.print("Cannot remove active branch! "
                    + "Please, change active branch first.");
            return;
        }
        
        appState.getCurrentRepo().createBranch(branchName);
        
        if (Settings.DEBUG_MODE)
            Console.print("create branch '" + branchName + "': SUCCESS");
    }

}
