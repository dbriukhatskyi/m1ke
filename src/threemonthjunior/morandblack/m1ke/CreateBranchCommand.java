/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * A command to create a new branch in current repository.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
class CreateBranchCommand extends AbstractCommand implements Command {

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
        
        appState.getCurrentRepo().createBranch(branchName);
        
        if (Settings.DEBUG_MODE)
            Console.print("create branch '" + branchName + "': SUCCESS");
    }

}
