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

        // Works by encoding each operation as a digit in an integer with base equal to the number of operations
        @SafeVarargs
        private boolean isSatisfyable(BiFunction<Long, Long, Long>... operations) {
            int radix = operations.length;
            int lastSequence = parseInt(Long.toString(radix - 1).repeat(right.size() - 1), radix);

            for (int operationSequence = 0; operationSequence <= lastSequence; operationSequence++) {
                String sequenceString = Integer.toString(operationSequence, radix);
                sequenceString = "0".repeat(right.size() - sequenceString.length() - 1) + sequenceString;

                long total = right.getFirst();
                for (int i = 1; i < right.size(); i++) {
                    total = operations[sequenceString.charAt(i - 1) - '0'].apply(total, right.get(i));
                }

                if (total == left) {
                    return true;
                }
            }

            return false;
        }
    }
}
