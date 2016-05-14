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
    private boolean quitRequest;
    private boolean initialized;
    
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
    
    /**
     * Mark the state as no longer needed by further application launches.
     */
    public void requestQuit() {
        quitRequest = true;
    }
    
    /**
     * Tells if the state was marked as no longer needed 
     * by further application launches.
     * 
     * @return true if the state is no longer needed
     */
    public boolean quitRequested() {
        return quitRequest;
    }
    
    /**
     * Mark the state as valid for further use.
     */
    public void initialize() {
        initialized = true;
    }
    
    /**
     * Check if the state is valid for further use.
     * 
     * @return true if the state is valid
     */
    public boolean isInitialized() {
        return initialized;
    }
    
}
