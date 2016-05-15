/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * A command to change current branch to a specified one.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
class GetBranchCommand extends AbstractCommand implements Command {

    /* (non-Javadoc)
     * @see threemonthjunior.morandblack.m1ke.Command#execute()
     */
    @Override
    public void execute(ApplicationState appState) {
        if (!validateStateAndRepo(appState))
            return;
        
        if (parametersEmpty()) {
            Console.print("Please supply branch name!");
            return;
        }
        
        String branchName = parameters[0];
        
        appState.getCurrentRepo().setActiveBranch(branchName);
        
        if (Settings.DEBUG_MODE)
            Console.print("get branch '" + branchName + "': SUCCESS");
    }

}
