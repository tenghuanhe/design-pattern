package visitor;

/**
 * Created by tenghuanhe on 2016/2/26.
 * ������ģʽ����ҪĿ����Ϊ���ܹ���һ��ͳһ�Ľṹ���칹������з���
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
