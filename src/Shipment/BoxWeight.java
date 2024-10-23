package Shipment;

public class BoxWeight extends Box{
    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public BoxWeight(int weight, int width, int height, int depth){
        super(width,height,depth);
        this.weight=weight;
    }
}
