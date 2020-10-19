/*

THIS CLASS WAS COPIED FROM https://exceptionshub.com/random-weighted-selection-in-java.html I ACCEPT NO CREDIT FOR THIS PORTION OF THE CODE

I will work out my own solution to the random weighted selection later.
 */
package Helpers;

import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

public class RandomCollection<E> {
    private final NavigableMap<Double, E> map = new TreeMap<Double, E>();
    private final Random random;
    private double total = 0;

    public RandomCollection() {
        this(new Random());
    }

    public RandomCollection(Random random) {
        this.random = random;
    }

    public RandomCollection<E> add(double weight, E result) {
        if (weight <= 0) return this;
        total += weight;
        map.put(total, result);
        return this;
    }

    public E next() {
        double value = random.nextDouble() * total;
        return map.higherEntry(value).getValue();
    }
}
