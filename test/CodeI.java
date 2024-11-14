package test;
import java.util.List;

public interface CodeI {
    List<Colour> getCode();
    int getLength();
    int[] howManyCorrect(CodeI other);
}
