/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


/**
 * Helper class to provide a convenient way to process files under version 
 * control.
 * 
 * @author 
 *
 */
final class FileUtils {
    
    /** A helper for converting a byte array to a hex string*/
    private final static char[] hexArray = "0123456789abcdef".toCharArray();
    
    /** Non-instantiable */
    private FileUtils() {}
    
    /**
     * <p>Converts a byte array to a hex string.</p>
     * 
     * <a href="http://stackoverflow.com/a/9855338">Taken from here</a>
     * 
     * @param bytes
     *        array to convert
     *        
     * @return the hex string generated from given array
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        
        return new String(hexChars);
    }
    
    /**
     * Calculates the given file's hash which is a hex string where 
     * first 64 bits (16 chars) are file size 
     * and the next 160 bits (40 chars) - SHA-1 hash 
     * 
     * @param pathToFile
     *        path to a file to get hash for 
     * 
     * @return hash string
     * 
     * @throws IllegalArgumentException
     *         if the path provided doesn't exist or doesn't represent a file
     */
    public static String calculateHash(Path pathToFile) {
        if (!Files.exists(pathToFile))
            throw new IllegalArgumentException(
                    "Path doesn't exist: '" + pathToFile + "'");
        
        if (!Files.isRegularFile(pathToFile))
            throw new IllegalArgumentException(
                    "Path doesn't represent a file: '" + pathToFile + "'");
        
        // TODO calc hash, see md5_tip.java but use sha-1 instead 
        
        /*
         * get file size from Files.getSize(Path) and calc SHA-1, then concatenate 
         */
        
        return null;
    }
    
    /**
     * Checks if contents of the two files are identical
     * 
     * @return true if and only if both files exist 
     *         and their contents are identical.
     */
    public static boolean contentEquals(Path file1, Path file2) {
        // TODO this would probably never be called but should be implemented
        
        return false;
    } 
        
    /**
     * Recursively traces both given DirectoryState objects and returns a list 
     * of absolute paths to files that were changed. 
     * 
     * @param oldState
     *        the old directory state
     * 
     * @param newState
     *        the new directory state to get changed files from
     *        
     * @param basicPath
     *        a path to a directory, change of state of which 
     *        is to be determined  
     * 
     * @return the list of files that changed
     * 
     * @see java.nio.file.Paths#get(String, String...)
     * @see java.nio.file.Path#resolve(Path)
     */
    public static List<Path> getFilesToStore(
            DirectoryState oldState,
            DirectoryState newState,
            Path basicPath)
    {
        /*
         * will need some additional helper recursive method.
         * 
         * 1) Get all files from basic directory
         * 2) Compare them one-by-one
         * 3) Add every changed file's absolute path to some temporary list
         *    (use resolve here to build absolute path)
         * 4) call 1) for every subdirectory
         * 
         * hint: the helper recursive method may use List<Path> parameter 
         * to pass a list to add paths to between recursive calls
         * 
         */
        
        // TODO implement
        return null;
    }

}
