public class Main {

    public static void main(String[] args) {
        Component espresso = new ConcreateComponent();
        System.out.println(espresso.add());

        Component americano = new WaterDecorator(new ConcreateComponent());
        System.out.println(americano.add());

        Component latte = new MilkDecorator(new ConcreateComponent());
        System.out.println(latte.add());

    }
}
