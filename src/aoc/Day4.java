package aoc;

import static aoc.Utils.allPoints;

import java.util.List;
import java.util.stream.Stream;

public class Day4 implements Day {

    @Override
    public long part1(List<String> input) {
        return allPoints(input.getFirst().length(), input.size())
                .mapToLong(point -> matchesStartingAt(input, point.x(), point.y()))
                .sum();
    }

    private long matchesStartingAt(List<String> input, int x, int y) {
        return Stream.of(
                List.of(-1, -1), List.of(-1, 0), List.of(-1, 1),
                List.of(0, -1),                  List.of(0, 1),
                List.of(1, -1),  List.of(1, 0),  List.of(1, 1)
        ).filter(Δ -> isMatch(input, x, y, Δ.get(0), Δ.get(1), 0))
                .count();
    }

    private boolean isMatch(List<String> input, int x, int y, int Δx, int Δy, int steps) {
        if (steps == 4) {
            return true;
        }

        try {
            return input.get(y + Δy * steps).charAt(x + Δx * steps) == "XMAS".charAt(steps)
                    && isMatch(input, x, y, Δx, Δy, steps + 1);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    @Override
    public long part2(List<String> input) {
        int count = 0;
        for (int x = 1; x < input.getFirst().length() - 1; x++) {
            for (int y = 1; y < input.size() - 1; y++) {
                if (input.get(y).charAt(x) != 'A') {
                    continue;
                }

                char northwest = input.get(y - 1).charAt(x - 1);
                char northeast = input.get(y - 1).charAt(x + 1);
                char southwest = input.get(y + 1).charAt(x - 1);
                char southeast = input.get(y + 1).charAt(x + 1);

                if (isMS(northwest, southeast) && isMS(northeast, southwest)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isMS(char c1, char c2) {
        return (c1 == 'M' && c2 == 'S')
                || (c1 == 'S' && c2 == 'M');
    }
}
