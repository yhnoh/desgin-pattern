public class WaterDecorator extends Decorator{

    public WaterDecorator(Component coffeeComponent) {
        super(coffeeComponent);
    }

    @Override
    public String add() {
        return super.add() + " + 물";
    }
}
