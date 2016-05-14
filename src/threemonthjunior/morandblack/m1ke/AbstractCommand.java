/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * An abstract implementation of the {@code Command} interface to implement 
 * a dummy configure() method for the commands that don't need configuration.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
abstract class AbstractCommand implements Command {


    /* (non-Javadoc)
     * @see threemonthjunior.morandblack.m1ke.Command#configure(java.lang.String[])
     */
    @Override
    public void configure(String[] parameters) {
        // do nothing
    }

}
