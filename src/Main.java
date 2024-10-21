import FunctionExecution.ConstantManager;
import FunctionExecution.ParsingTree;
import FunctionParsing.FunctionParser;
import UserInterface.UserInterface;

public class Main {
    public static void main(String[] args) {
        (new UserInterface()).setVisible(true);

        //parsing tree test
//        String func="artan(z+0)+((a^b)+c)";
//        ParsingTree parsed=FunctionParser.parse(func);
//        System.out.println(parsed);
    }
}