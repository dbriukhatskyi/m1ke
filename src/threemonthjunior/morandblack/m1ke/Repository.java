/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author 
 *
 */
class Repository {
    
    /** A directory under version control with m1ke */
    private Path workingPath;
    
    /** A directory with m1ke service info (commits, branches etc.) */
    private Path servicePath;
    
    /** Cached ID of the latest commit in the active branch */
    private String headPointer;
    
    /** Name of the active branch */
    private String activeBranch;
    
    /** File storage containing all files in single place */
    private FileStorage storage;
    
    /**
     * Creates a new instance. Cannot be called from the client code.
     * 
     * @throws IOException 
     * 
     */
    private Repository(Path basePath) throws IOException {
        workingPath = basePath;
        servicePath = basePath.resolve(Paths.get(Settings.REPO_SUB_PATH));
        
        // TODO read current branch name
        
        Files.createDirectory(workingPath);
    }
    
    private DirectoryState getDirStateForCommit(String commitId) {
        // TODO implement
        
        return null;
    }
    
    /**
     * 
     * @return active branch name
     */
    public String getActiveBranch() {
        return activeBranch; 
    }
    
    /**
     * 
     * @param branchName
     *        the name of a branch to set as active
     */
    public void setActiveBranch(String branchName) {
        // TODO write active branch name to disk
        
        // TODO validate input, throw an exception
        
        // TODO set headPointer to a latest branch commit 
        
        // TODO recursively restore directory state from all commits
        
        activeBranch = branchName;
    }
    
    /**
     * 
     * @param branchName
     *        the name of a new branch to create
     */
    public void createBranch(String branchName) {
        // TODO check if a branch with such name already exists
        
        // TODO create a new branch on disk
        
        // TODO change current branch only on success
        
        // TODO write current headPointer in the branch config on disk
        
        activeBranch = branchName;
    }
    
    /**
     * 
     * @param branchName
     */
    public void removeBranch(String branchName) {
        // TODO check if a branch exists and remove it from disk
        
        // TODO process a situation with deletion of an active branch
    }
    
    /**
     * <p>Checks if the specified directory contains a valid repository and 
     * uses the existing one or initializes a new one.</p> 
     * <p>After that a newly created class instance is initialized with
     * the settings from the aforementioned service directory 
     * and returned from the method.</p>  
     * 
     * @param directory
     *        a Path instance representing a directory under version control
     *        
     * @return a newly created Repository instance
     */
    public static Repository forDirectory(Path directory) {
        // TODO check if the specified directory contains a valid repo
        
        // TODO read repository from disk or create a new one on the disk 
        
        return null;
    }
    
    public void Commit(String message) {
        // TODO generate commit ID
        // TODO read latest commit ID
        
        // TODO create a FileSystemState to write to disk
        
        // TODO write the newly created FileSystemState
    }

    
}
