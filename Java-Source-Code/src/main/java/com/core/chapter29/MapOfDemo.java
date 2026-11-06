package com.core.chapter29;

import java.util.HashMap;
import java.util.Map;

public class MapOfDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = Map.of("Bob", 1, "Charlie", 2, "Den", 3);
      //  map.put("Den", 4); // UnsupportedOperationException
        System.out.println(map);
    //    Map<String, Integer> map2 = Map.of(null, 1, "Charlie", 2);
     //   System.out.println(map2); // NullPointerException
     //   Map<String, Integer> map3 = Map.of("Bob", 1, "Charlie", null);
    //    System.out.println(map3); // NullPointerException
     //   Map<String, Integer> map4 = Map.of("Bob", 1, "Bob", 1);
      //  System.out.println(map4); // IllegalArgumentException
    }
}
