/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

/**
 * A command to print program usage to console.
 * 
 * @author Dmytro Briukhatskyi
 *
 */
class PrintUsageCommand extends AbstractCommand implements Command {

    /* (non-Javadoc)
     * @see threemonthjunior.morandblack.m1ke.Command#execute()
     */
    @Override
    public void execute(ApplicationState appState) {
        Console.print(Settings.USAGE_MESSAGE);
    }

}
