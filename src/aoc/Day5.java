package aoc;

import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day5 implements Day {

    @Override
    public long part1(List<String> input) {
        Map<Long, List<Long>> banRules = input.stream()
                .filter(line -> line.contains("|"))
                .collect(groupingBy(
                        line -> parseLong(line.split("\\|")[1]),
                        mapping(line -> parseLong(line.split("\\|")[0]),
                                toList())
                ));

        return input.stream()
                .filter(line -> line.contains(","))
                .map(line -> stream(line.split(","))
                        .map(Long::parseLong)
                        .toList()
                )
                .filter(pages -> {
                    Set<Long> bannedPages = new HashSet<>();
                    for (long page : pages) {
                        if (bannedPages.contains(page)) {
                            return false;
                        }
                        bannedPages.addAll(banRules.getOrDefault(page, List.of()));
                    }
                    return true;
                })
                .mapToLong(pages -> pages.get(pages.size() / 2))
                .sum();
    }

    @Override
    public long part2(List<String> input) {
        return 0;
    }
}
