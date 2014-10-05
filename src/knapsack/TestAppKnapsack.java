/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static knapsack.Knapsack.zeroOneKnapsack;

/**
 *
 * @author RainWhileLoop
 */
public class TestAppKnapsack {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Integer number;
        Float weight;
        Float value;
        Float conditionMaxWeight;
        Item item;
        List<Item> items = new ArrayList<>();
        System.out.print("Input number of item : ");
        number = scan.nextInt();

        for (int i = 0; i < number; i++) {
            System.out.print("enter weight : ");
            weight = scan.nextFloat();
            System.out.print("enter value : ");
            value = scan.nextFloat();
            item = new Item(weight, value);
            item.setId(i);
            items.add(item);
        }

        System.out.print("enter Max Weight : ");
        conditionMaxWeight = scan.nextFloat();
        Knapsack.fractionKnapsack(items, conditionMaxWeight);
        Float maxValue = Knapsack.getMaxValue();
        Float maxWeight = Knapsack.getMaxWeight();
        System.out.println("Max value = " + maxValue);
        System.out.println("Max weight = " + maxWeight);

    }

}
