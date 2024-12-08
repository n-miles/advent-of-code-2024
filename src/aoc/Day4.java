package aoc;

import java.util.List;
import java.util.stream.Stream;

public class Day4 implements Day {
    @Override
    public long part1(List<String> input) {
        long count = 0;
        for (int x = 0; x < input.getFirst().length(); x++) {
            for (int y = 0; y < input.size(); y++) {
                count += matchesStartingAt(input, x, y);
            }
        }
        return count;
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
        return 0;
    }
}