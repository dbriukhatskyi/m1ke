/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 *  Package-global constants collected in one place for convenience.
 *
 */
public final class Settings {
    
    /** Non-instantiable */
    private Settings() {}
    
    /** Repository service subdirectory relative path */
    public final static String REPO_SUB_PATH = ".m1ke";
    
    /** Default initial branch name for creating a new repository */
    public final static String DEFAULT_BRANCH_NAME = "master"; 
    
    /** Application state storage file name */
    public final static String APPSTATE_FILE_NAME = "m1ke.state"; 
    
    // TODO write full usage instructions
    /** Usage message to print when the command is not recognized */
    public final static String USAGE_MESSAGE = "usage: m1ke <command> [parameters]";

    /** Print debug messages */ 
    public static final boolean DEBUG_MODE = false;     
    
}
