package aoc;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;

import java.util.List;
import java.util.function.BiFunction;

public class Day7 implements Day {
    @Override
    public long part1(List<String> input) {
        return sumOfSatisfyable(input,
                (a, b) -> a + b,
                (a, b) -> a * b
        );
    }

    @Override
    public long part2(List<String> input) {
        return sumOfSatisfyable(input,
                (a, b) -> a + b,
                (a, b) -> a * b,
                (a, b) -> parseLong(a + "" + b)
        );
    }

    @SafeVarargs
    private long sumOfSatisfyable(List<String> input, BiFunction<Long, Long, Long>... operations) {
        return input.stream()
                .map(line -> {
                    String[] sides = line.split(": ");

                    return new Equation(
                            parseLong(sides[0]),
                            stream(sides[1].split(" ")).map(Long::parseLong).toList()
                    );
                })
                .filter(equation -> equation.isSatisfyable(operations))
                .mapToLong(Equation::left)
                .sum();
    }

    private record Equation(long left, List<Long> right) {

        @SafeVarargs
        private boolean isSatisfyable(BiFunction<Long, Long, Long>... operations) {
            int opCount = operations.length;
            int permutations = pow(opCount, right.size());

            for (int opSequence = 0; opSequence < permutations; opSequence++) {
                long total = right.getFirst();
                for (int i = 1; i < right.size(); i++) {
                    int operation = (opSequence / pow(opCount, i - 1)) % opCount;
                    total = operations[operation].apply(total, right.get(i));
                }

                if (total == left) {
                    return true;
                }
            }

            return false;
        }
    }

    private static int pow(int base, int exp) {
        return (int) Math.pow(base, exp);
    }
}
