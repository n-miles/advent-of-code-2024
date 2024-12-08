package aoc;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Day1 implements Day {

    @Override
    public long part1(List<String> input) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (String line : input) {
            String[] numbers = line.split("\\s+");
            left.add(Integer.parseInt(numbers[0]));
            right.add(Integer.parseInt(numbers[1]));
        }

        left.sort(Integer::compareTo);
        right.sort(Integer::compareTo);

        long sum = 0;
        while (!left.isEmpty()) {
            sum += Math.abs(left.removeLast() - right.removeLast());
        }
        return sum;
    }

    @Override
    public long part2(List<String> input) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (String line : input) {
            String[] numbers = line.split("\\s+");
            left.add(Integer.parseInt(numbers[0]));
            right.add(Integer.parseInt(numbers[1]));
        }

        Map<Integer, Long> rightCounts = right.stream()
                .collect(groupingBy(i -> i, counting()));

        return left.stream()
                .mapToLong(i -> i * rightCounts.getOrDefault(i, 0L))
                .sum();
    }
}
