package com.core.newJavaFeatures;

import java.util.*;

/*
class User {
    String name;
    int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return age;
    }

    public boolean equals(Object another) {
        if (another == this) return true;
        if (!(another instanceof User)) return false;
        User anotherUser = (User) another;
        return Objects.equals(anotherUser.getName(), this.name) &&
                Objects.equals(anotherUser.getAge(), this.age);
    }

    public int hashCode() {
        return Objects.hash(this.name, this.age);
    }
}

 */

record User(String name, int age) {}

public class HashMapExample {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>(Arrays.asList(
            new User("Abhishek", 28),
            new User("Anil", 45),
            new User("Shubham", 34),
            new User("Anil", 45)));

        HashSet<User> uniqueUsers = new HashSet<>();

        Iterator<User> userIterator = users.iterator();

        while (userIterator.hasNext()) {
            User current = userIterator.next();
            if (!uniqueUsers.add(current)) {
                userIterator.remove();
            }
        }

        Set<User> seenUsers = new HashSet<>();

        // removeIf returns true if the element should be deleted
        users.removeIf(user -> !seenUsers.add(user));





        users.forEach(user -> System.out.println(user.name() + " " + user.age()));
    }
}
