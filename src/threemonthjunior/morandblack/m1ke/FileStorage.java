/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

import java.io.InputStream;

/**
 * <p>A centralized storage of all the files for the repository.</p>
 * 
 * <p>Stores every files in some "bin" which is chosen 
 * by the file contents' checksum. If several files have the same checksum,
 * each of them is given an additional ordinal number to distinguish 
 * between them inside a bin. </p> 
 * 
 * <p>The implementation of this interface is supposed to be able not to store 
 * the files with identical contents more than once.</p>
 * 
 * @see threemonthjunior.morandblack.m1ke.FileIdentifier
 * 
 * @author Dmytro Briukhatskyi
 * 
 */
interface FileStorage {
    
    /**
     * Stores given file contents and returns its identifier within the storage. 
     * 
     * @param data
     *        InputStream attached to contents of the file to store
     * 
     * @return stored file's unique identifier within the storage
     */
    FileIdentifier storeFileAndGetID (InputStream data);
    
    /**
     * Returns an InputStream attached to contents of the file to get.
     * 
     * @param id
     *        file's unique identifier within the storage
     * 
     * @return
     */
    InputStream getFileById(FileIdentifier id);
}
