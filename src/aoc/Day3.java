package aoc;

import static java.lang.Long.parseLong;
import static java.lang.String.join;

import java.util.List;
import java.util.regex.Pattern;

public class Day3 implements Day {

    @Override
    public long part1(List<String> input) {
        return input.stream()
                .flatMapToLong(line -> Pattern.compile("mul\\((\\d+),(\\d+)\\)").matcher(line).results()
                        .mapToLong(match -> parseLong(match.group(1)) * parseLong(match.group(2)))
                ).sum();
    }

    @Override
    public long part2(List<String> input) {
        var matcher = Pattern.compile("mul\\((\\d+),(\\d+)\\)|don't\\(\\)|do\\(\\)")
                .matcher(join(" ", input));

        long total = 0;
        boolean doo = true;
        while (matcher.find()) {
            var instruction = matcher.group(0);
            if (instruction.equals("do()")) {
                doo = true;
            } else if (instruction.equals("don't()")) {
                doo = false;
            } else {
                if (doo) {
                    total += parseLong(matcher.group(1)) * parseLong(matcher.group(2));
                }
            }
        }

        return total;
    }
}
