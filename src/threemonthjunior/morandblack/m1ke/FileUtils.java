/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
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
    
    /**
     * Checks if provided directory is empty.
     * 
     * @param directory
     *        a path to check
     * 
     * @return true if provided directory is empty
     * 
     * @throws IOException
     *         if directory could not be read 
     *         (for example, if it doesn't exist 
     *          or if the provided path is not a directory) 
     */
    public static boolean dirIsEmpty(Path directory) throws IOException {
        try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
            return !dirStream.iterator().hasNext();
        }
    }

    /**
     * Copy directory contents to another directory, 
     * optionally excluding given path. All Path parameters should be absolute.
     * 
     * Borrowed from <a href = "http://stackoverflow.com/questions/6214703/copy-entire-directory-contents-to-another-directory"> source </a>
     * 
     * @param src
     *        source path
     * 
     * @param dest
     *        destination path
     *        
     * @param excluding
     *        <em>absolute</em> path to exclude from copying
     *        
     * @throws IOException
     */
    public static void copyDirectoryContents(
            Path src, 
            Path dest, 
            Path... excluding) throws IOException 
    {
        Path pathToExclude;
        
        if (excluding.length < 1)
            pathToExclude = null;
        else
            pathToExclude = excluding[0];
        
        Files.walkFileTree(src, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(
                    final Path dir,
                    final BasicFileAttributes attrs) 
                            throws IOException 
            {
                if (pathToExclude == null || !dir.startsWith(pathToExclude))
                     Files.createDirectories(dest.resolve(src.relativize(dir)));
                
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(
                    final Path file,
                    final BasicFileAttributes attrs) 
                            throws IOException 
            {
                if (pathToExclude == null || !file.startsWith(pathToExclude))
                     Files.copy(file, dest.resolve(src.relativize(file)));

                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * A helper method to clean a directory contents recursively.
     * 
     * Borrowed from <a href = "http://www.adam-bien.com/roller/abien/entry/java_7_deleting_recursively_a">here</a>
     * 
     * @param directory
     *        path to a directory to delete
     * 
     * @param excludeDir
     *        path to a directory to exclude from deletion
     * 
     * @throws IOException
     *         if were unable to clean the directory due to an I/O exception
     */
    public static void cleanDirRecursively(Path directory, String excludeDir) 
            throws IOException 
    {
        if (!Files.exists(directory)) 
            return;
        
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) 
                    throws IOException 
            {
                if (!file.toString().contains(excludeDir))
                    Files.delete(file);

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) 
                    throws IOException 
            {
                if (!dir.equals(directory) && !dir.toString().contains(excludeDir))
                    Files.delete(dir);

                return FileVisitResult.CONTINUE;
            }

        });
    }
 
}
