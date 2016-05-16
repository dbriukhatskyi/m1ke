/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * <p>A repository representation.</p>
 * <p>This class provides functionality to create repository for specified path 
 * or to read an existing one from that path.</p> 
 * <p>The state is saved directly to disk and is read directly from disk 
 * for each commit or branch operation.</p>
 * <p>The only way to get an instance of this class is as follows: </p>
 * {@code Repository repo = Repository.forDirectory(Paths.get("/path/to/dir"));}
 * 
 * @author Dmytro Briukhatskyi
 * 
 */
class Repository {
  
    private static final String BRANCH_CONFIG_FILE_NAME = "active_branch.txt";
    private static final Path branchesSubDir = Paths.get("branches/");
    private static final Path commitSubDir = Paths.get("commits/");
    private static final Path storageSubDir = Paths.get("file-storage/");
    
    /** A directory under version control with m1ke */
    private Path workingPath;
    
    /** A directory with m1ke service info (commits, branches etc.) */
    private Path servicePath;
    
    /** A directory with m1ke file storage */
    private Path storagePath;

    /** A directory with m1ke commits */
    private Path commitsPath;
    
    /** A directory with m1ke branches */
    private Path branchesPath;
    
    /** Cached ID of the latest commit in the active branch */
    private String headPointer = "";
    
    /** File storage containing all files in single place */
    private FileStorage storage;
    
    /**
     * Creates a new instance. Cannot be called from the client code.
     * 
     * @param basePath
     *        a path to a directory under version control
     */
    private Repository(Path basePath) {
        workingPath = basePath;
        servicePath = basePath.resolve(Paths.get(Settings.REPO_SUB_PATH));
        storagePath = servicePath.resolve(storageSubDir);
        commitsPath = servicePath.resolve(commitSubDir);
        branchesPath = servicePath.resolve(branchesSubDir);
    }
    
    /**
     * Reads directory state from the commit.
     * 
     * @param commitId
     *        commit ID to get directory state from
     * 
     * @return {@link DirectoryState} object containing directory state 
     *         for the specified commit 
     */
    private DirectoryState getDirStateForCommit(String commitId) {
        // TODO implement
        
        return null;
    }
    
    /**
     * Read from disk and return an active branch name. 
     * If none is found, null is returned.
     * 
     * @return active branch name if such branch exists,
     *         null otherwise
     */
    public String getActiveBranch() {
        Path branchConfigPath = branchesPath.resolve(BRANCH_CONFIG_FILE_NAME);
        
        if (!Files.exists(branchConfigPath))
            return null;
        
        String activeBranch = null;
        
        try (InputStream confInputStream = Files.newInputStream(branchConfigPath); 
             Scanner confScanner = new Scanner(confInputStream, "UTF-8"))
        {
            if (confScanner.hasNext())
                activeBranch = confScanner.next();
        } catch (IOException e) {
            Console.print("ERROR: Could not read an active branch from disk!");
            e.printStackTrace();
        }
        
        return activeBranch; 
    }
    
    /**
     * <p>Sets an active branch and optionally loads it's last commit.</p>
     * <p>The {@code loadCommit} parameter should only be false when creating 
     * the new branch, i.e. when this method is called 
     * from {@link #createBranch(String)}.</p>
     * 
     * @param branchName
     *        the name of a branch to set as active
     *        
     * @param loadCommit 
     *        true if the last commit from the newly chosen branch 
     *        should be loaded into the working directory
     */
    public void setActiveBranch(String branchName, boolean loadCommit) {
        Path branchConfigPath = branchesPath.resolve(BRANCH_CONFIG_FILE_NAME);
        Path branchPath = branchesPath.resolve(branchName);
        
        if (!Files.exists(branchPath)) {
            Console.print("ERROR: Branch '"
                    + branchName + "'"
                    + " not found!");
            
            return;
        }
        
        try (Writer configWriter = Files.newBufferedWriter(
                branchConfigPath, 
                Charset.forName("UTF-8"))) 
        {
            configWriter.append(branchName);
        } catch (IOException e) {
            Console.print("ERROR: Branch change error: "
                    + System.lineSeparator()
                    + " branch config file '"
                    + branchConfigPath
                    + "' could not be modified!");
            
            return;
        }
        
        if (loadCommit) {
            loadHeadPointer();
            restoreCommit(headPointer);
        }
        
        // TODO validate input, throw an exception
        
        // TODO set headPointer to a latest branch commit 
        
        // TODO recursively restore directory state from all commits
        
        
    }

    /**
     * Creates a new branch with specified name starting at the current commit.
     * 
     * @param branchName
     *        the name of a new branch to create
     */
    public void createBranch(String branchName) {
        // TODO check if a branch with such name already exists
        // TODO validate branch name
  
        loadHeadPointer();
        storeHeadPointer(branchName);
        setActiveBranch(branchName, false);
    }
    
    /**
     * Removes an existing branch. 
     * 
     * @param branchName
     */
    public void removeBranch(String branchName) {
        // TODO check if a branch exists and remove it from disk
        // TODO process a situation with deletion of an active branch
        
        if (branchName == null || branchName.equals(getActiveBranch())) 
            return;
        
        Path branchSubPath = Paths.get(branchName);
        Path branchPath = branchesPath.resolve(branchSubPath);
        
        try {
            Files.deleteIfExists(branchPath);
        } catch (IOException e) {
            Console.print("ERROR: could not remove branch file!");
        }

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
        
        if (!Files.isDirectory(directory)) {
            Console.print("Repository creation/opening error: "
                    + System.lineSeparator()
                    + "'" + directory + "'"
                    + " is not a directory!");
            
            return null;
        }
        
        Repository repo = new Repository(directory);
        
        try {
            Files.createDirectories(repo.storagePath);
            Files.createDirectories(repo.commitsPath);
            Files.createDirectories(repo.branchesPath);
        } catch (IOException e) {
            Console.print("Repository creation/opening error: "
                    + System.lineSeparator()
                    + " service directory could not be created!");
        }
        
        try {
            if (FileUtils.dirIsEmpty(repo.branchesPath)) {
                Console.print("m1ke found there was no branch here"
                        + System.lineSeparator()
                        + "'" + Settings.DEFAULT_BRANCH_NAME + "'"
                        + " branch was chosen!");
                repo.createBranch(Settings.DEFAULT_BRANCH_NAME);
            }
        } catch (IOException e) {
            Console.print("Repository creation/opening error: "
                    + System.lineSeparator()
                    + "'" + directory + "'"
                    + " contents could not be read!");
            
            return null;
        }
        
        return repo;
    }
    
    /**
     * Commits current changes.
     * 
     * @param message
     *        commit message
     * 
     */
    public void Commit(String message) {
        // generate commit ID of current date and time with milliseconds
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "yyyyMMddAAAAAAAA"); 
        String commitId = LocalDateTime.now().format(formatter);
        Path commitDir = commitsPath.resolve(Paths.get(commitId));
        Path commitDataDir = commitDir.resolve(Paths.get("data"));
        
        loadHeadPointer();
        
        try {
            Files.createDirectories(commitDir);
            savePrevCommitId(commitDir);
            saveCommitMessage(commitDir, message);
        } catch (IOException e) {
            Console.print("ERROR: Commit failed due to an I/O Exception!");
        }

        headPointer = commitId;
        storeWorkingDir(commitId, commitDataDir);
        storeHeadPointer(getActiveBranch());
        
        // TODO create a DirectoryState to write to disk
        
        // TODO write the newly created DirectoryState
    }
    
    /**
     * Restores specified commit's working directory state.
     * 
     * @param commitId
     *        ID of commit to restore the directory state from
     */
    private void restoreCommit(String commitId) {
        Path commitDir = commitsPath.resolve(Paths.get(commitId));
        Path commitDataDir = commitDir.resolve(Paths.get("data"));
        
        try {
            FileUtils.cleanDirRecursively(workingPath, Settings.REPO_SUB_PATH);
        } catch (IOException e) {
            Console.print("ERROR: Could not clear working directory!");
        }
        
        try {
            FileUtils.copyDirectoryContents(commitDataDir, workingPath);
        } catch (IOException e) {
            Console.print("ERROR: Could not restore working directory snapshot!");
        }
    }

    /**
     * Saves commit message to a file.
     * 
     * @param commitDir
     *        commit service data directory 
     *      
     * @param message
     *        commit message to save
     */
    private void saveCommitMessage(Path commitDir, String message) {
        try (Writer messageWriter = Files.newBufferedWriter(
                commitDir.resolve(Paths.get("message.txt")), 
                Charset.forName("UTF-8"))) 
        {
            messageWriter.append(message);
        } catch (IOException e) {
            Console.print("ERROR: Unable to store commit message!");
        }
    }

    /**
     * Read current head pointer from a current branch config file.
     */
    private void loadHeadPointer() {
        String branch = getActiveBranch();
        
        if (branch == null) {
            return;
        }
        
        Path branchSubPath = Paths.get(branch);
        Path branchPath = branchesPath.resolve(branchSubPath);
        
        try (InputStream configInputStream = Files.newInputStream(branchPath);
             Scanner configScanner = new Scanner(configInputStream, "UTF-8"))
        {
            if (configScanner.hasNext())
                headPointer = configScanner.next();
            
        } catch (IOException e) {
            Console.print("ERROR: could not get head pointer!");
        }
    }

    /**
     * Store current head pointer to a specified branch config file.
     * 
     * @param branchName
     *        name of a branch where to store head pointer
     */
    private void storeHeadPointer(String branchName) {
        Path branchSubPath = Paths.get(branchName);
        Path branchPath = branchesPath.resolve(branchSubPath);
        
        try (Writer configWriter = Files.newBufferedWriter(
                branchPath, 
                Charset.forName("UTF-8"))) 
        {
            configWriter.append(headPointer);
        } catch (IOException e) {
            Console.print("ERROR: Branch state saving error: "
                    + System.lineSeparator()
                    + " branch file '"
                    + branchPath
                    + "' could not be modified!");
            
            return;
        }
    }

    /**
     * A helper to save a pointer to the previous commit 
     * in the current commit directory.
     * 
     * @param commitDir
     *        service directory of the commit which should store the pointer
     * 
     * @throws IOException
     *         if an I/O exception occurred during operation
     */
    private void savePrevCommitId(Path commitDir) throws IOException {
        try (Writer lastCommitWriter = Files.newBufferedWriter(
                commitDir.resolve(Paths.get("prev-commit")), 
                Charset.forName("UTF-8"))) 
        {
            lastCommitWriter.append(headPointer);
        }
    }

    /**
     * A temporary simplified implementation of the storage model.
     * 
     * @param commitId
     *        commit ID
     */
    private void storeWorkingDir(String commitId, Path commitDataDir) {
        try {
            Files.createDirectories(commitDataDir);
            FileUtils.copyDirectoryContents(workingPath, commitDataDir, servicePath);
        } catch (IOException e) {
            Console.print("ERROR: Snapshot saving error!");
        }
        

    }
    
}
