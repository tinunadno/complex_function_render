package FunctionExecution;

public interface Function2<FirstArgumentType, SecondArgumentType, OutPutType> {
    OutPutType apply(FirstArgumentType a, SecondArgumentType b);
}
