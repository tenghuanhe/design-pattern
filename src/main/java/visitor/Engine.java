package visitor;

/**
 * Created by tenghuanhe on 2016/2/26.
 */
public class Engine {
    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
