package FunctionParsing;

import FunctionExecution.ParsingTree;

import java.util.HashSet;
import java.util.Stack;

public class FunctionParser {
    private static final HashSet<Character> operation = new HashSet<>();

    static {
        operation.add('+');
        operation.add('-');
        operation.add('/');
        operation.add('*');
        operation.add('^');
        operation.add('(');
        operation.add(')');
    }

    //preparing string for parsing
    public static ParsingTree parse(String function) {
        function = function.replace(" ", "");
        return stringToTree(function);
    }

    //converts mathematical equation with variables and functions to compilable tree
    private static ParsingTree stringToTree(String function) {
        ParsingTree currentNode = new ParsingTree();
        ParsingTree ret = currentNode;
        Stack<ParsingTree> parsingTreeNode = new Stack<>();
        for (int i = 0; i < function.length(); i++) {
            if (function.charAt(i) == '(') {
                ParsingTree newNode = new ParsingTree();
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);
                } else if (currentNode.getLeft().getFunction() != null && (currentNode.getLeft().getOperation()==null)) {
                    newNode.setFunction(currentNode.getLeft().getFunction());
                    currentNode.setLeft(newNode);
                } else if (currentNode.getRight() == null) {
                    currentNode.setRight(newNode);
                } else if (!(currentNode.getLeft() == null) && !(currentNode.getLeft().getFunction() == null)) {
                    newNode.setFunction(currentNode.getLeft().getFunction());
                    currentNode.setLeft(newNode);
                } else {
                    newNode.setFunction(currentNode.getRight().getFunction());
                    currentNode.setRight(newNode);
                }
                parsingTreeNode.push(currentNode);
                currentNode = newNode;
            } else if (function.charAt(i) == ')') {
                currentNode = parsingTreeNode.pop();
            } else if (!operation.contains(function.charAt(i))) {
                String currentArgument = "";
                while (!operation.contains(function.charAt(i))) {
                    currentArgument += function.charAt(i++);
                    if (i == function.length()) break;
                }
                if (i != function.length() - 1) {
                    i--;
                }
                ParsingTree newNode;
                if (!(i == function.length() - 1 || function.charAt(i + 1) != '(')) {
                    newNode = new ParsingTree(null);
                    newNode.setFunction(currentArgument);
                } else {
                    newNode = new ParsingTree(currentArgument);
                }
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);
                } else {
                    currentNode.setRight(newNode);
                }

            } else if (operation.contains(function.charAt(i))) {
                currentNode.setOperation(function.charAt(i) + "");
            }
        }
        return ret;
    }
}