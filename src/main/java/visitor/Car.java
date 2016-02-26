package visitor;

/**
 * Created by tenghuanhe on 2016/2/26.
 */
public class Car {
    private Engine engine = new Engine();
    private Body body = new Body();
    private Wheel[] wheels = {
            new Wheel("front left"), new Wheel("front right"),
            new Wheel("back left"), new Wheel("back right")
    };

    void accept(Visitor visitor) {
        visitor.visit(this);
        engine.accept(visitor);
        body.accept(visitor);
        for (Wheel wheel : wheels) {
            wheel.accept(visitor);
        }
    }
}
