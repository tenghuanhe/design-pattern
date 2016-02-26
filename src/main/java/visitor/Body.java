package visitor;

/**
 * Created by tenghuanhe on 2016/2/26.
 */
public class Body {
    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
