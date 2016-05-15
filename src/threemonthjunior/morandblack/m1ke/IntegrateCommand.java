/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A command to integrate specified path to VCS.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
class IntegrateCommand extends AbstractCommand implements Command {

    /* (non-Javadoc)
     * @see threemonthjunior.morandblack.m1ke.Command#execute()
     */
    @Override
    public void execute(ApplicationState appState) {
        if (!validateState(appState)) 
            return;
        
        if (parametersEmpty()) {
            Console.print("Please supply a path to integrate!");
            return;
        } 
        
        Path workingDirectory = Paths.get(parameters[0]);
        
        if (!workingDirectory.isAbsolute()) {
            Path basePath = Paths.get(System.getProperty("user.dir"));
            workingDirectory = basePath.resolve(workingDirectory);
        }
        
        if (!Files.isDirectory(workingDirectory)) {
            Console.print("Specified path is not a directory!");
            return;
        }
        
        appState.setWorkingDirectory(workingDirectory);
        
        if (Settings.DEBUG_MODE)
            Console.print("integrate '" + workingDirectory + "': SUCCESS");
    }

}
