/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * A structure to store the file's unique identifier within a storage.
 * Identifier consists of the string representation of checksum and
 * the ordinal number of the unique file with such checksum.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
class FileIdentifier {
    
    /** File checksum */
    private String hash;
    
    /** File position within that hash */
    private long position;

    /**
     * @return the checksum
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @return the position
     */
    public long getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(long position) {
        this.position = position;
    }
    
}
