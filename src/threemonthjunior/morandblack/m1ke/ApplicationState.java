/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

import java.nio.file.Path;

/**
 * 
 * A class to store the current application state: 
 * <ul>
 * <li>current working directory</li>
 * <li>current repository</li>
 * </ul>
 * 
 * @author Dmytro Briukhatskyi
 *
 */
class ApplicationState {
    private Path workingDirectory;
    private Repository currentRepo;
    
    /**
     * @return the workingDirectory
     */
    public Path getWorkingDirectory() {
        return workingDirectory;
    }
    
    /**
     * @param workingDirectory the workingDirectory to set
     */
    public void setWorkingDirectory(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
    }
    
    /**
     * @return the currentRepo
     */
    public Repository getCurrentRepo() {
        return currentRepo;
    }
    
    /**
     * @param currentRepo the currentRepo to set
     */
    public void setCurrentRepo(Repository currentRepo) {
        this.currentRepo = currentRepo;
    }

}
