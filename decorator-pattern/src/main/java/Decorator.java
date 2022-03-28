public abstract class Decorator implements Component{
    private Component coffeeComponent;

    public Decorator(Component coffeeComponent) {
        this.coffeeComponent = coffeeComponent;
    }

    @Override
    public String add() {
        return coffeeComponent.add();
    }
}
