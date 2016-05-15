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
     * @return the working directory
     */
    public Path getWorkingDirectory() {
        return workingDirectory;
    }
    
    /**
     * Sets working directory to the specified one and gets repository for 
     * that directory. If no repository found, the new one is created. 
     * 
     * @param workingDirectory 
     *        the working directory to set
     */
    public void setWorkingDirectory(Path workingDirectory) {
        this.workingDirectory = workingDirectory;
        
        this.currentRepo = Repository.forDirectory(workingDirectory);
    }
    
    /**
     * @return the currentRepo
     */
    public Repository getCurrentRepo() {
        return currentRepo;
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
