package aoc;

import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;

import java.util.List;

public class Day7 implements Day {
    @Override
    public long part1(List<String> input) {
        return input.stream()
                .map(line -> {
                    String[] sides = line.split(": ");

                    return new Equation(
                            parseLong(sides[0]),
                            stream(sides[1].split(" ")).map(Long::parseLong).toList()
                    );
                })
                .filter(Equation::isPart1Satisfyable)
                .mapToLong(Equation::left)
                .sum();
    }

    @Override
    public long part2(List<String> input) {
        return 0;
    }

    private record Equation(long left, List<Long> right) {

        boolean isPart1Satisfyable() {
            long magicLimit = (1L << (right.size() - 1)) - 1;
            for (int magic = 0; magic <= magicLimit; magic++) {
                long total = right.getFirst();
                for (int i = 0; i < right.size() - 1; i++) {
                    long nextNumber = right.get(i + 1);
                    total = (magic & (1L << i)) == 0 ? total + nextNumber : total * nextNumber;
                }

                if (total == left) {
                    return true;
                }
            }
            return false;
        }
    }
}
