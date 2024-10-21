package FunctionExecution;

public class ParsingTree {
    private String operation;
    private ParsingTree left;
    private ParsingTree right;
    private String function;

    public ParsingTree() {
        operation = null;
        left = null;
        right = null;
    }

    public ParsingTree(String operation) {
        this.operation = operation;
    }

    public String toString() {
        if (left == null && right == null) return "(" + operation + ")";
        String anw = "(" + operation + "):";
        if (function != null) anw += "{" + function + "}";
        if (left != null) anw += "[" + left + ", ";
        else anw += "[null, ";
        if (right != null) anw += right + "]";
        else anw += "null]";
        return anw;
    }

    public String asMathematicalExpression() {
        if (left == null && right == null) return operation;
        String anw = "";
        if (function != null) anw += function;
        anw += "(";
        if (left != null)
            anw += left.asMathematicalExpression();
        if (operation != null)
            anw += operation;
        if (right != null)
            anw += right.asMathematicalExpression();
        return anw + ")";
    }

    public Complex compile() {
        if (left == null && right == null){
            try {
                return new Complex(Double.parseDouble(operation), 0);
            }catch (NumberFormatException e){
                Complex temp=ConstantManager.getConstantByName(operation);
                return temp;
            }
        }
        if (function != null)
            return MathematicalOperationExecute.executeOperation(MathematicalOperationExecute.executeOperation(left.compile(), right.compile(), operation), function);
        return MathematicalOperationExecute.executeOperation(left.compile(), right.compile(), operation);
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setLeft(ParsingTree left) {
        this.left = left;
    }

    public void setRight(ParsingTree right) {
        this.right = right;
    }
    public String getOperation(){return operation;}

    public ParsingTree getRight() {
        return right;
    }

    public ParsingTree getLeft() {
        return left;
    }

    public String getFunction() {
        return function;
    }

}
