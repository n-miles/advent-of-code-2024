package aoc;

import static aoc.Utils.allPoints;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.stream.Stream;

public class Day8 implements Day {

    @Override
    public long part1(List<String> input) {
        return allPoints(input.getFirst().length(), input.size())
                .filter(point -> input.get(point.y()).charAt(point.x()) != '.')
                .collect(groupingBy(point -> input.get(point.y()).charAt(point.x())))
                .values().stream()
                .flatMap(Utils::allCombinationsUnordered)
                .flatMap(antennaPair -> {
                    int Δx = antennaPair.left().x() - antennaPair.right().x();
                    int Δy = antennaPair.left().y() - antennaPair.right().y();
                    return Stream.of(
                            new Point(antennaPair.left().x() + Δx, antennaPair.left().y() + Δy),
                            new Point(antennaPair.right().x() - Δx, antennaPair.right().y() - Δy)
                    );
                })
                .filter(antinode -> antinode.x() >= 0 && antinode.y() >= 0
                        && antinode.x() < input.getFirst().length() && antinode.y() < input.size())
                .distinct()
                .count();
    }

    @Override
    public long part2(List<String> input) {
        return 0;
    }
}
