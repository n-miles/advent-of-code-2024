package aoc;

import static java.lang.Long.parseLong;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day11 implements  Day {

    @Override
    public long part1(List<String> input) {
        Map<Pair<Long, Integer>, Long> cache = new HashMap<>();
        long sum = 0;
        for (String rock : input.getFirst().split(" ")) {
            sum += countRocks(parseLong(rock), 25, cache);
        }
        return sum;
    }

    @Override
    public long part2(List<String> input) {
        Map<Pair<Long, Integer>, Long> cache = new HashMap<>();
        long sum = 0;
        for (String rock : input.getFirst().split(" ")) {
            sum += countRocks(parseLong(rock), 75, cache);
        }
        return sum;
    }

    private long countRocks(long rock, int blinksRemaining, Map<Pair<Long, Integer>, Long> cache) {
        if (blinksRemaining == 0) {
            return 1;
        }
        Pair<Long, Integer> currentPosition = new Pair<>(rock, blinksRemaining);
        Long cached = cache.get(currentPosition);
        if (cached != null) {
            return cached;
        }

        blinksRemaining--;

        // DIY lexical scope that can yield
        long result = switch(0) {default -> {
            if (rock == 0) {
                yield countRocks(1, blinksRemaining, cache);
            }

            long digits = (long) (Math.log10(rock) + 1);
            if (digits % 2 == 0) {
                long dividend = (long) Math.pow(10, digits / 2);
                long left = rock / dividend;
                long right = rock % dividend;
                yield countRocks(left, blinksRemaining, cache) + countRocks(right, blinksRemaining, cache);
            }

            yield countRocks(rock * 2024, blinksRemaining, cache);
        }};

        cache.put(new Pair<>(rock, blinksRemaining + 1), result);
        return result;
    }
}
