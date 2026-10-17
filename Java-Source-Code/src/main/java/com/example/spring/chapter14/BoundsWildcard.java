package com.example.spring.chapter14;

// Bounded wildcard arguments
// Two dimensional coordinates
class TwoD {
    int x, y;
    TwoD(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

// Three dimensional coordinates
class ThreeD extends TwoD {
    int z;
    ThreeD(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }
}

// Four dimensional coordinates
class FourD extends ThreeD {
    int t;
    FourD(int a, int b, int c, int d) {
        super(a, b, c);
        this.t = d;
    }
}
// this class holds an array of coordinates array
class Coords<T extends TwoD> {
    T[] coords;

    Coords(T[] o) {
        this.coords = o;
    }
}

public class BoundsWildcard {
    static void showXY(Coords<?> c) {
        System.out.println("X Y coordinates");
        for (int i = 0, n = c.coords.length; i < n; ++i) {
            System.out.println(c.coords[i].x + " " + c.coords[i].y);
        }
        System.out.println();
    }

    static void showXYZ(Coords<? extends ThreeD> c) {
        System.out.println("X Y Z Coordinates");
        for (int i = 0, n = c.coords.length; i < n; ++i) {
            System.out.println(c.coords[i].x + " " + c.coords[i].y + " " + c.coords[i].z);
        }
        System.out.println();
    }

    static void showAll(Coords<? extends FourD> c) {
        System.out.println("X Y Z T Coordinates");
        for (int i = 0, n = c.coords.length; i < n; ++i) {
            System.out.println(c.coords[i].x + " " + c.coords[i].y + " " + c.coords[i].z + " " + c.coords[i].t);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TwoD td[] = {
                new TwoD(0, 0),
                new TwoD(7, 8),
                new TwoD(9, 4),
                new TwoD(-2, -3)
        };

        Coords<TwoD> tdlocs = new Coords<TwoD>(td);

        System.out.println("Contents of tdlocs: ");
        showXY(tdlocs);
        //  showXYZ(tdlocs);  // error won't compile
        // showAll(tdlocs); // error won't compile

        // Now create some Fourd objects
        FourD fd[] = {
                new FourD(1, 2, 3, 4),
                new FourD(6, 8, 14, 18),
                new FourD(22, 2, 9, -4),
                new FourD(3, -2, -34, 17)
        };

        Coords<FourD> fdlocs = new Coords<FourD>(fd);

        System.out.println("Contents of fdlocs");
        // These are all OK.
        showXY(fdlocs);
        showXYZ(fdlocs);
        showAll(fdlocs);
    }
}
