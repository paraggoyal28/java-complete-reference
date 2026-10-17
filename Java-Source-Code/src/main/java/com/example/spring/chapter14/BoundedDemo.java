package com.example.spring.chapter14;

class Stats<T extends Number> {
    T[] nums; // array of Number or subclasses

    Stats(T[] o) {
        nums = o;
    }

    // Return type double in all cases
    double average() {
        double sum = 0.0;

        for (int i = 0;i < nums.length; ++i) {
            sum += nums[i].doubleValue();
        }

        return sum / nums.length;
    }
}

public class BoundedDemo {
    public static void main(String[] args) {

        Integer inums[] = {1, 2, 3, 4, 5, 6};
        Stats<Integer> iob = new Stats<>(inums);
        double v = iob.average();
        System.out.println("iOb average is: " + v);

        Double dnums[] = {-1.1, 2.2, 3.3, 4.4, 5.5};
        Stats<Double> dob = new Stats<>(dnums);
        double w = dob.average();
        System.out.println("dob average is: " + w);

        // below won't compile because String not a subclass of number
     //   String strs[] = {"1", "2", "3", "4", "5", "6"};
     //   Stats<String> strOb = new Stats<>(strs);



    }
}
