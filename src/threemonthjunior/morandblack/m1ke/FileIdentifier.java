/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * @author Dmytro Briukhatskyi
 *
 */
class FileIdentifier {
    
    /** File checksum */
    private String checksum;
    
    /** File position within that checksum */
    private long position;

    /**
     * @return the checksum
     */
    public String getChecksum() {
        return checksum;
    }

    /**
     * @param checksum the checksum to set
     */
    public void setChecksum(String checksum) {
        this.checksum = checksum;
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
