package FunctionExecution;

import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.function.Function;

public class MathematicalOperationExecute {
    private static final HashMap<String, Function2<Complex, Complex, Complex>> availableOperations = new HashMap<>();
    private static final TreeMap<String, String> availableOperationsDescription=new TreeMap<>();
    private static final HashMap<String, Function<Complex, Complex>> availableFunctions = new HashMap<>();
    private static final TreeMap<String, String> availableFunctionDescription=new TreeMap<>();

    static {
        availableOperations.put("+", (Complex::add));
        availableFunctionDescription.put("+", "sum of two complex numbers");
        availableOperations.put("-", (Complex::sub));
        availableFunctionDescription.put("-", "difference of two complex numbers");
        availableOperations.put("*", (Complex::mult));
        availableFunctionDescription.put("*", "multiply two complex numbers");
        availableOperations.put("/", (Complex::div));
        availableFunctionDescription.put("/", "divide two complex numbers");
        availableOperations.put("^", (Complex::pow));
        availableFunctionDescription.put("^", "raise a complex number to the power of complex number");

        availableFunctions.put("sin", Complex::sin);
        availableFunctionDescription.put("sin", "trigonometric sine of a complex number");
        availableFunctions.put("cos", Complex::cos);
        availableFunctionDescription.put("cos", "trigonometric cosine of a complex number");
        availableFunctions.put("sec", Complex::sec);
        availableFunctionDescription.put("sec", "trigonometric secant of a complex number");
        availableFunctions.put("csc", Complex::csc);
        availableFunctionDescription.put("csc", "trigonometric cosecant of a complex number");
        availableFunctions.put("tan", Complex::tan);
        availableFunctionDescription.put("tan", "trigonometric tangent of a complex number");
        availableFunctions.put("cot", Complex::cot);
        availableFunctionDescription.put("cot", "trigonometric cotangent of a complex number");

        availableFunctions.put("sinh", Complex::sinh);
        availableFunctionDescription.put("sinh", "hyperbolic sine of a complex number");
        availableFunctions.put("cosh", Complex::cosh);
        availableFunctionDescription.put("cosh", "hyperbolic cosine of a complex number");
        availableFunctions.put("sech", Complex::sech);
        availableFunctionDescription.put("sech", "hyperbolic secant of a complex number");
        availableFunctions.put("csch", Complex::csch);
        availableFunctionDescription.put("csch", "hyperbolic cosecant of a complex number");
        availableFunctions.put("tanh", Complex::tanh);
        availableFunctionDescription.put("tanh", "hyperbolic tangent of a complex number");
        availableFunctions.put("coth", Complex::coth);
        availableFunctionDescription.put("coth", "hyperbolic cotangent of a complex number");

        availableFunctions.put("asin", Complex::asin);
        availableFunctionDescription.put("asin", "inverse trigonometric sine of a complex number");
        availableFunctions.put("acos", Complex::acos);
        availableFunctionDescription.put("acos", "inverse trigonometric cosine of a complex number");
        availableFunctions.put("asec", Complex::asec);
        availableFunctionDescription.put("asec", "inverse trigonometric secant of a complex number");
        availableFunctions.put("acsc", Complex::acsc);
        availableFunctionDescription.put("acsc", "inverse trigonometric cosecant of a complex number");
        availableFunctions.put("atan", Complex::atan);
        availableFunctionDescription.put("atan", "inverse trigonometric tangent of a complex number");
        availableFunctions.put("acot", Complex::acot);
        availableFunctionDescription.put("acot", "inverse trigonometric cotangent of a complex number");

        availableFunctions.put("arsin", Complex::arsin);
        availableFunctionDescription.put("arsin", "area-sine of a complex number");
        availableFunctions.put("arcos", Complex::arcos);
        availableFunctionDescription.put("arcos", "area-cosine of a complex number");
        availableFunctions.put("arsec", Complex::arsec);
        availableFunctionDescription.put("arsec", "area-secant of a complex number");
        availableFunctions.put("arcsc", Complex::arcsc);
        availableFunctionDescription.put("arcsc", "area-cosecant of a complex number");
        availableFunctions.put("artan", Complex::artan);
        availableFunctionDescription.put("artan", "area-tangent of a complex number");
        availableFunctions.put("arcot", Complex::arcot);
        availableFunctionDescription.put("arcot", "area-cotangent of a complex number");

        availableFunctions.put("ln", Complex::log);
        availableFunctionDescription.put("ln", "natural log of a complex number");
    }

    public static Complex executeOperation(Complex a, Complex b, String operation) {
        if (availableOperations.containsKey(operation)) {
            return (availableOperations.get(operation)).apply(a, b);
        }
        return null;
    }

    public static Complex executeOperation(Complex a, String function) {
        if (availableFunctions.containsKey(function)) {
            return (availableFunctions.get(function)).apply(a);
        }
        return null;
    }
    public static String getOperationsDescription(){
        String description="";
        for(String operationName:availableOperationsDescription.keySet()){
            description+=operationName+": "+availableOperationsDescription.get(operationName)+"\n";
        }
        description+="\n";
        for(String functionName:availableFunctionDescription.keySet()){
            description+=functionName+": "+availableFunctionDescription.get(functionName)+"\n";
        }
        return description;
    }
}
