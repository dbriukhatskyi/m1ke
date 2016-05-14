/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

import java.util.HashMap;

/**
 * @author Dmytro Briukhatskyi
 *
 */
final class CommandLineDispatcher {
    
    /** Map linking a command line parameter name to a Command instance */
    private static HashMap<String, Command> cmdMap = new HashMap<>();
    
    static {
        cmdMap.put("init", new InitCommand());
        cmdMap.put("integrate", new IntegrateCommand());
        cmdMap.put("save", new SaveCommand());
        cmdMap.put("quit", new QuitCommand());
        cmdMap.put("create-branch", new CreateBranchCommand());
        cmdMap.put("get-branch", new GetBranchCommand());
        cmdMap.put("remove-branch", new RemoveBranchCommand());
        cmdMap.put(null, new PrintUsageCommand());
    }

    /** Noninstantiable */
    private CommandLineDispatcher() {}
    
    /**
     * Returns a Command instance to execute depending on the command-line arguments.
     * 
     * @param args
     *        command-line arguments list
     *        
     * @return Command class instance, ready to use
     */
    public static Command getCommand(String[] args) {
        // TODO implement selection of an appropriate action
        return cmdMap.get(null);
    }
}
