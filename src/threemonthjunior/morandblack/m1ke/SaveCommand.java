/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * A command to save current working directory snapshot into commit.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
class SaveCommand extends AbstractCommand implements Command {

    /* (non-Javadoc)
     * @see threemonthjunior.morandblack.m1ke.Command#execute()
     */
    @Override
    public void execute(ApplicationState appState) {
        if (!validateStateAndRepo(appState))
            return;
        
        String commitMessage = "";
        
        if (parametersEmpty()) {
            commitMessage = getNewCommitMessage();
        } else {
            if (parameters.length > 2 && parameters[1].equals("-m"))
                commitMessage = parameters[2];
            
            if (commitMessage.trim().isEmpty())
                commitMessage = getNewCommitMessage();
        }
        
        appState.getCurrentRepo().Commit(commitMessage);
        
        if (Settings.DEBUG_MODE)
            Console.print("save -m '" + commitMessage + "': SUCCESS");
    }

    /**
     * A helper method to get a commit message if it wasn't provided earlier.
     * 
     * @return a new commit message
     */
    private String getNewCommitMessage() {
        String commitMessage = "";
        
        while (commitMessage.trim().isEmpty()) {
            Console.print("Please provide non-empty commit message:");
            commitMessage = Console.getInput();
        }
        
        return commitMessage;
    }

}
