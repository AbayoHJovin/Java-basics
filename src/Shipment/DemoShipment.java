package Shipment;

public class DemoShipment {
    public static void main (String[] args){
        Shipment shipment = new Shipment(10,20,40,50,60);
        System.out.println("The volume of the shipment is " + shipment.volume());
        System.out.println("The weight of the shipment is " + shipment.getWeight());
        System.out.println("The cost of the shipment is " + shipment.getCost());
    }
}
