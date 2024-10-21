package Shape;

public class Circle implements Shape {
    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    private int radius;
    @Override
    public double area() {
        return radius * radius*Shape.PI;
    }

    @Override
    public void draw() {

    }

    @Override
    public void print() {

    }
}

