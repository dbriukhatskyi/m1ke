/**
 * 
 */
package threemonthjunior.morandblack.m1ke;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Dmytro Briukhatskyi
 *
 */
final class CommandLineDispatcher {
    
    /** Map linking a command line parameter name to a Command instance */
    private static HashMap<String, Command> cmdMap = 
            new HashMap<String, Command>() { {
                put("init", new InitCommand());
                put("integrate", new IntegrateCommand());
                put("save", new SaveCommand());
                put("quit", new QuitCommand());
                put("create-branch", new CreateBranchCommand());
                put("get-branch", new GetBranchCommand());
                put("remove-branch", new RemoveBranchCommand());
                put(null, new PrintUsageCommand());
            } };

    /** Non-instantiable */
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
        String cmdName = null;
        String[] parameters = null;  
        
        if (args.length > 0) {
            cmdName = args[0];
            
            if (args.length > 1) 
                parameters = Arrays.copyOfRange(args, 1, args.length);
        }
        
        Command cmd = cmdMap.get(cmdName);
        
        if (cmd == null)
            cmd = cmdMap.get(null);
        
        cmd.configure(parameters);
        
        return cmd;
    }
}
