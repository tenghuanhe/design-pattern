package visitor;

/**
 * Created by tenghuanhe on 2016/2/26.
 */
public class Wheel {
    private String name;

    Wheel(String name) {
        this.name = name;
    }

    String getName() {
        return this.name;
    }

    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
