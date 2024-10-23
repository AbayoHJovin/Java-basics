package Shipment;

public class Shipment extends BoxWeight{
    private int cost;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Shipment(int cost, int weight, int height, int depth, int width){
        super(weight,height,depth,width);
        this.cost=cost;
    }

}
