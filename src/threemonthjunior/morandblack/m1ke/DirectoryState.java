/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

import java.io.Serializable;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Directory state storage
 * 
 * @author Dmytro Briukhatskyi
 *
 */
class DirectoryState implements Serializable{

    private static final long serialVersionUID = 5729927383797695382L;
    
    private String name;
    private List<DirectoryState> subdirs;
    private Map<String, BasicFileAttributes> files;
    private Map<String, FileIdentifier> identifiers;

    public DirectoryState(String name) {
        this.name = name;

        subdirs = new ArrayList<>();
        files = new HashMap<>();
        identifiers = new HashMap<>();
    }

    /**
     * @return the name of the directory
     */
    public String getName() {
        return name;
    }

    /**
     * @return the subdirectories list
     */
    public List<DirectoryState> getSubdirectories() {
        return subdirs;
    }

    /**
     * Adds a subdirectory into this directory.
     * 
     * @param subdir the subdirectory to add
     */
    public void addSubdirectory(DirectoryState subdir) {
        subdirs.add(subdir);
    }

    /**
     * @return the list of files in the directory
     */
    public Map<String, BasicFileAttributes> getFiles() {
        return files;
    }

    /**
     * Adds a file to a directory.
     * 
     * @param name
     * @param attributes
     */
    public void addFile(String name, BasicFileAttributes attributes) {
        files.put(name, attributes);
    }

    /**
     * Sets an identifier for a given file name.
     * 
     * @param name
     *        file name
     * 
     * @param identifier
     *        identifier to set
     */
    public void setFileIdentifier(String name, FileIdentifier identifier) {
        identifiers.put(name, identifier);
    }

    /**
     * Returns last modification time of a specified file.
     * 
     * @param name
     *        file name
     * 
     * @return last modification time
     */
    public FileTime getFileModificationTime(String name) {
        return files.get(name).lastModifiedTime();
    }
}
