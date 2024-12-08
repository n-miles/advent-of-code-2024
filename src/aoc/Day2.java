package aoc;

import static java.util.Arrays.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day2 implements Day {
    @Override
    public long part1(List<String> input) {
        return parseReports(input)
                .filter(this::isSafe)
                .count();
    }

    private boolean isSafe(List<Integer> report) {
        report = new ArrayList<>(report);
        int previous = report.removeFirst();
        List<Integer> deltas = new ArrayList<>();
        while (!report.isEmpty()) {
            int current = report.removeFirst();
            deltas.add(current - previous);
            previous = current;
        }

        return (deltas.stream().allMatch(i -> i > 0) || deltas.stream().allMatch(i -> i < 0))
                && deltas.stream().allMatch(i -> Math.abs(i) <= 3);
    }

    @Override
    public long part2(List<String> input) {
        return parseReports(input)
                .filter(report -> {
                    if (isSafe(report)) {
                        return true;
                    }

                    for (int i = 0; i < report.size(); i++) {
                        var copy = new ArrayList<>(report);
                        copy.remove(i);
                        if (isSafe(copy)) {
                            return true;
                        }
                    }

                    return false;
                })
                .count();
    }

    private Stream<List<Integer>> parseReports(List<String> lines) {
        return lines.stream()
                .map(line -> stream(line.split(" "))
                        .map(Integer::parseInt)
                        .toList()
                );
    }
}
