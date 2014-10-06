package knapsack;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author RainWhileLoop
 */
public class Knapsack {

    private Float maxWeight;
    private Float maxValue;

    /**
     * zeroOneKnapsack use for find max possible maxValue by<br/>
     * <b>sum of maxWeight</b> &lt; <b>condition of max maxWeight</b><br/>
     * and unnecessary to find a ratio of improper fraction
     *
     * @param items a List of Item class
     * @param conditionMaxWeight a condition of max maxWeight
     */
    public void zeroOneKnapsack(List<Item> items, Float conditionMaxWeight) {
        Float value = 0.0f;
        Float weight = 0.0f;
//        for (Item item : items) {
//            System.out.println("Item NO." + item.getId() + "\tweight : " + item.getWeight() + "\tvalue : " + item.getValue() + "\tratio : " + item.getRatio());
//        }
        Collections.sort(items, compare());
        int i = 0;
        do {
            weight += items.get(i).getWeight();
            if (weight < conditionMaxWeight) {
                value += items.get(i).getValue();
            }
            i++;
        } while (i < items.size() && weight < conditionMaxWeight);
        weight -= items.get(--i).getWeight();
//        System.out.println("after sorting by ratio --------------------");

        for (Item item : items) {
//            System.out.println("Item NO." + item.getId() + "\tweight : " + item.getWeight() + "\tvalue : " + item.getValue() + "\tratio : " + item.getRatio());
        }
//        System.out.println("Max Weight = " + weight);
        this.maxWeight = weight;
        this.maxValue = value;
    }

    /**
     * fractionKnapsack use for find max possible maxValue by<br/>
     * fitting <b>sum of maxWeight</b> = <b>condition of max maxWeight</b> <br/>
     * and unnecessary to find a ratio of improper fraction
     *
     * @param items a List of Item class
     * @param conditionMaxWeight a condition of max maxWeight
     */
    public void fractionKnapsack(List<Item> items, Float conditionMaxWeight) {
        Float sumValue = 0.0f;
        Float sumWeight = 0.0f;
//        for (Item item : items) {
//            System.out.println("Item NO." + item.getId() + "\tweight : " + item.getWeight() + "\tvalue : " + item.getValue() + "\tratio : " + item.getRatio());
//        }

        Collections.sort(items, compare());
        int i = 0;
        do {
            Item currentItem = items.get(i);
            Item beforeItem = null;
            if (i > 0) {
                beforeItem = items.get(i - 1);
            }
            sumWeight += currentItem.getWeight();
            if (sumWeight < conditionMaxWeight) {
                sumValue += currentItem.getValue();
            } else {
                sumValue -= beforeItem.getValue();
                sumWeight -= currentItem.getWeight();
                sumWeight -= beforeItem.getWeight();
//                System.out.println("maxWeight of item \t: " + beforeItem.getWeight());
//                System.out.println("sumWeight \t: " + sumWeight);
                Float difference = conditionMaxWeight - sumWeight;
//                System.out.println("diff \t\t: " + difference);

                Float ratio = beforeItem.getWeight() / difference;
//                System.out.println("ratio \t\t: " + ratio);

                sumWeight += difference;
//                System.out.println("sumValue \t: " + sumValue);
                sumValue += beforeItem.getValue() * ratio;
            }
            i++;
        } while (i < items.size() && sumWeight < conditionMaxWeight);

//        System.out.println("after sorting by ratio --------------------");
//        for (Item item : items) {
//            System.out.println("Item NO." + item.getId() + "\tweight : " + item.getWeight() + "\tvalue : " + item.getValue() + "\tratio : " + item.getRatio());
//        }
//        System.out.println("Max Weight = " + sumWeight);
        this.maxWeight = sumWeight;
        this.maxValue = sumValue;
    }

    public Float getMaxWeight() {
        return maxWeight;
    }

    public Float getMaxValue() {
        return maxValue;
    }

    private static Comparator compare() {
        Comparator itemComparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Item i1 = (Item) o1;
                Item i2 = (Item) o2;
                return i2.compareTo(i1);
            }
        };

        return itemComparator;
    }

}
