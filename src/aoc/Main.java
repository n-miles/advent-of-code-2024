package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var days = List.of(new Day1(), new Day2(), new Day3(), new Day4(), new Day5(), new Day6(), new Day7(),
                new Day8(), new Day9(), new Day10(), new Day11());

        for (var day : days) {
            String name = day.getClass().getSimpleName();
            System.out.println(name + " ------------------------------------------------------");
            for (var inputPath : getInputs(name)) {
                var inputLines = Files.readAllLines(inputPath);
                try {
                    System.out.println(inputPath.getFileName() + " - Part 1: " + day.part1(inputLines));
                } catch (RuntimeException e) {
                    System.out.println(inputPath.getFileName() + " - Part 1: FAILED");
                    e.printStackTrace(System.out);
                }
                try {
                    System.out.println(inputPath.getFileName() + " - Part 2: " + day.part2(inputLines));
                } catch (RuntimeException e) {
                    System.out.println(inputPath.getFileName() + " - Part 2: FAILED");
                    e.printStackTrace(System.out);
                }
                System.out.println();
            }
        }
    }

    private static List<Path> getInputs(String name) throws IOException {
        return Files.find(Path.of("input"), 1,
                (path, _) -> path.getFileName().toString().startsWith(name))
                .toList().reversed();
    }
}
