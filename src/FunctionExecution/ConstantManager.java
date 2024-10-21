package FunctionExecution;

import java.util.HashMap;

public class ConstantManager {
    private static final HashMap<String, Complex> generalConstants=new HashMap<>();
    private static HashMap<String, Complex> changeableConstants=new HashMap<>();
    static{
        generalConstants.put("i", new Complex(0, 1));
        generalConstants.put("PI", new Complex(Math.PI, 0));

        changeableConstants.put("z", new Complex(1, 0));
        changeableConstants.put("c", new Complex(1,0));
    }
    public static Complex getConstantByName(String name){
        if(generalConstants.containsKey(name)){
            return generalConstants.get(name);
        }
        if(changeableConstants.containsKey(name)){
            return changeableConstants.get(name);
        }
        return null;
    }

    public static void addConstant(String name, Complex constant){
        changeableConstants.put(name, constant);
    }
}
