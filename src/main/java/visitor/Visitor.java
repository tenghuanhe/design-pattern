package visitor;

/**
 * Created by tenghuanhe on 2016/2/26.
 */
public interface Visitor {
    void visit(Wheel wheel);

    void visit(Engine engine);

    void visit(Body body);

    void visit(Car car);
}
