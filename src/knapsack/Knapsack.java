/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author RainWhileLoop
 */
public abstract class Knapsack {

    private static Float maxWeight;
    private static Float maxValue;

    /**
     * zeroOneKnapsack use for find max possible maxValue by<br/>
     * <b>sum of maxWeight</b> &lt; <b>condition of max maxWeight</b><br/>
     * and unnecessary to find a ratio of improper fraction
     *
     * @param items a List of Item class
     * @param conditionMaxWeight a condition of max maxWeight
     */
    public static void zeroOneKnapsack(List<Item> items, Float conditionMaxWeight) {
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
//
//        for (Item item : items) {
//            System.out.println("Item NO." + item.getId() + "\tweight : " + item.getWeight() + "\tvalue : " + item.getValue() + "\tratio : " + item.getRatio());
//        }
//        System.out.println("Max Weight = " + possibleWeight);
        Knapsack.maxWeight = weight;
        Knapsack.maxValue = value;
    }

    /**
     * fractionKnapsack use for find max possible maxValue by<br/>
     * fitting <b>sum of maxWeight</b> = <b>condition of max maxWeight</b> <br/>
     * and unnecessary to find a ratio of improper fraction
     *
     * @param items a List of Item class
     * @param conditionMaxWeight a condition of max maxWeight
     */
    public static void fractionKnapsack(List<Item> items, Float conditionMaxWeight) {
        Float sumValue = 0.0f;
        Float sumWeight = 0.0f;
//        for (Item item : items) {
//            System.out.println("Item NO." + item.getId() + "\tweight : " + item.getWeight() + "\tvalue : " + item.getValue() + "\tratio : " + item.getRatio());
//        }
        
        Collections.sort(items, compare());
        int i = 0;
        do {
            sumWeight += items.get(i).getWeight();
            if (sumWeight < conditionMaxWeight) {
                sumValue += items.get(i).getValue();
            } else {
                sumValue -= items.get(i - 1).getValue();
                sumWeight -= items.get(i).getWeight();
                sumWeight -= items.get(i - 1).getWeight();
//                System.out.println("maxWeight of item \t: " + items.get(i-1).getWeight());
//                System.out.println("sumWeight \t: " + sumWeight);
                Float difference = conditionMaxWeight - sumWeight;
//                System.out.println("diff \t\t: " + difference);

                Float ratio = items.get(i - 1).getWeight() / difference;
//                System.out.println("ratio \t\t: " + ratio);

                sumWeight += difference;
//                System.out.println("sumValue \t: " + sumValue);
                sumValue += items.get(i - 1).getValue() * ratio;
            }
            i++;
        } while (i < items.size() && sumWeight < conditionMaxWeight);

//        System.out.println("after sorting by ratio --------------------");
//
//        for (Item item : items) {
//            System.out.println("Item NO." + item.getId() + "\tweight : " + item.getWeight() + "\tvalue : " + item.getValue() + "\tratio : " + item.getRatio());
//        }
//        System.out.println("Max Weight = " + possibleWeight);
        Knapsack.maxWeight = sumWeight;
        Knapsack.maxValue = sumValue;
    }

    public static Float getMaxWeight() {
        return maxWeight;
    }

    public static Float getMaxValue() {
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
