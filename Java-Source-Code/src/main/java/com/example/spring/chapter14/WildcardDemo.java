package com.example.spring.chapter14;

// usage of wildcard

class StatsWildcard<T extends Number> {
    T[] nums; // array of numbers or its subtypes

    StatsWildcard(T[] o) {
        nums = o;
    }

    // Return type double in all cases
    double average() {
        double sum = 0.0;

        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i].doubleValue();
        }

        return sum / nums.length;
    }

    // Determine if two averages are the same
    // Notice the use of the wildcard
    boolean sameAvg(StatsWildcard<?> ob) {
        if (average() == ob.average()) {
            return true;
        }
        return false;
    }
}

public class WildcardDemo {
    public static void main(String[] args) {
        Integer iNums[] = {1, 2, 3, 4, 5};
        StatsWildcard<Integer> iOb = new StatsWildcard<>(iNums);
        double avg1 = iOb.average();
        System.out.println("Average of stats integer: " + avg1);

        Double dNums[] = {1.1, 2.2, 3.3, 4.4, 5.5};
        StatsWildcard<Double> dOb = new StatsWildcard<>(dNums);
        double w = dOb.average();
        System.out.println("Average of dob: " + w);

        Float fNums[] = {1.0F, 2.0F, 3.0F, 4.0F, 5.0F};
        StatsWildcard<Float> fOb = new StatsWildcard<>(fNums);
        double x = fOb.average();
        System.out.println("fOb average is: " + x);

        // see which arrays have same average
        System.out.print("Average of iOb and dOb: ");
        if (iOb.sameAvg(dOb)) {
            System.out.println("are the same");
        } else {
            System.out.println("differ");
        }

        System.out.print("Average of iOb and fOb: ");
        if (iOb.sameAvg(fOb)) {
            System.out.println("are the same.");
        } else {
            System.out.println("differ");
        }
    }
}
