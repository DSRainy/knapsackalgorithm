/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack;

/**
 *
 * @author RainWhileLoop
 */
public class Item {

    private int id;
    private Float weight;
    private Float value;
    private Float ratio;

    public Item(Float weight, Float value) {
        this.weight = weight;
        this.value = value;
        this.ratio = this.value / this.weight;
    }

    Item() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Float getRatio() {
        return ratio;
    }

    public Integer compareTo(Item that) {
        return this.ratio.compareTo(that.ratio);
    }

}
