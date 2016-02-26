package visitor;

/**
 * Created by tenghuanhe on 2016/2/26.
 * 访问者模式的主要目的是为了能够用一个统一的结构对异构对象进行访问
 */
public class PrintVisitor implements Visitor {

    public static void main(String[] args) {
        Car car = new Car();
        Visitor visitor = new PrintVisitor();
        car.accept(visitor);
    }

    public void visit(Wheel wheel) {
        System.out.println("Visiting " + wheel.getName() + " wheel");
    }

    public void visit(Engine engine) {
        System.out.println("Visiting engine");
    }

    public void visit(Body body) {
        System.out.println("Visiting body");
    }

    public void visit(Car car) {
        System.out.println("Visiting car");
    }
}
