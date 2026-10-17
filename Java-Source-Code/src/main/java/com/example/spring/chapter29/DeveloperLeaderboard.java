package com.example.spring.chapter29;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Scenario: You are building a leaderboard feature. You have a list of Developer objects. Each developer has a name (String), experienceYears (int), and solvedProblemsCount (int).
Write a method using Java 8 Streams to sort the developers based on the following multi-tiered business logic:
1. Primary Sort: By solvedProblemsCount in descending order (most solved problems first).
2. Secondary Sort: If two developers solved the same number of problems, sort them by experienceYears in ascending order (younger/less experienced talent gets higher rank for high efficiency).
3. Tertiary Sort: If problems and experience are identical, sort them by name alphabetically (case-insensitive)
 */

class Developer {
    private final String name;
    private final int experienceInYears;
    private final int solvedProblemsCount;

    public Developer(String name, int experienceInYears, int solvedProblemsCount) {
        this.name = name;
        this.experienceInYears = experienceInYears;
        this.solvedProblemsCount = solvedProblemsCount;
    }

    public String getName() {
        return name;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }

    public int getSolvedProblemsCount() {
        return solvedProblemsCount;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "name='" + name + '\'' +
                ", experienceInYears=" + experienceInYears +
                ", solvedProblemsCount=" + solvedProblemsCount +
                '}';
    }
}

public class DeveloperLeaderboard {
    public static void main(String[] args) {
        List<Developer> developers = Arrays.asList(
                new Developer("Amit", 5, 120),
                new Developer("Rahul", 3, 120),
                new Developer("sita", 8, 150),
                new Developer(null, 3, 120)
        );

        List<Developer> rankedList = developers.stream()
                .sorted(
                        // 1. Primary: by solved Problems count
                        Comparator.comparingInt(Developer::getSolvedProblemsCount).reversed()
                        // 2. By experience in years if solved same number of problems
                                .thenComparing(Developer::getExperienceInYears)
                        // 3. If same experience and same number of solved problems, then by name case in insensitive
                                .thenComparing(Developer::getName, Comparator.nullsLast(String::compareTo))
                ).toList();

        System.out.println("LeaderBoard: "+ rankedList);
    }
}
