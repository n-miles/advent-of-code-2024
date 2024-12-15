package aoc;

import static aoc.Utils.allPoints;
import static aoc.Utils.charAt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Day8 implements Day {

    @Override
    public long part1(List<String> input) {
        return countAntinodes(input, antennaPair -> {
            int Δx = antennaPair.left().x() - antennaPair.right().x();
            int Δy = antennaPair.left().y() - antennaPair.right().y();
            return Stream.of(
                    new Point(antennaPair.left().x() + Δx, antennaPair.left().y() + Δy),
                    new Point(antennaPair.right().x() - Δx, antennaPair.right().y() - Δy)
            );
        });
    }

    @Override
    public long part2(List<String> input) {
        return countAntinodes(input, antennaPair -> {
            int Δx = antennaPair.left().x() - antennaPair.right().x();
            int Δy = antennaPair.left().y() - antennaPair.right().y();
            return range(-50, 50)
                    .mapToObj(multiplier -> new Point(
                            antennaPair.left().x() + multiplier * Δx,
                            antennaPair.left().y() + multiplier * Δy));
        });
    }

    private static long countAntinodes(List<String> input, Function<Pair<Point, Point>, Stream<Point>> antinodeGenerator) {
        return allPoints(input)
                .filter(point -> charAt(input, point) != '.')
                .collect(groupingBy(point -> charAt(input, point)))
                .values().stream()
                .flatMap(Utils::allCombinationsUnordered)
                .flatMap(antinodeGenerator)
                .filter(antinode -> antinode.x() >= 0 && antinode.y() >= 0
                        && antinode.x() < input.getFirst().length() && antinode.y() < input.size())
                .distinct()
                .count();
    }
}
