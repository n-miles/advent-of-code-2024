package aoc;

import static java.lang.Long.parseLong;
import static java.util.Arrays.stream;

import java.util.List;
import java.util.stream.LongStream;

public class Day11 implements  Day {

    @Override
    public long part1(List<String> input) {
        return stream(input.getFirst().split(" "))
                .mapToLong(Long::parseLong)
                .flatMap(rock -> afterBlinks(rock, 25))
                .count();
    }

    @Override
    public long part2(List<String> input) {
        if (true) {
            throw new RuntimeException("This will oom lol");
        }
        return stream(input.getFirst().split(" "))
                .mapToLong(Long::parseLong)
                .flatMap(rock -> afterBlinks(rock, 75))
                .count();
    }

    private LongStream afterBlinks(long rock, long remainingBlinks) {
        if (remainingBlinks == 0) {
            return LongStream.of(rock);
        }
        remainingBlinks--;

        if (rock == 0) {
            return afterBlinks(1, remainingBlinks);
        }

        String rockDigits = "" + rock;
        if (rockDigits.length() % 2 == 0) {
            return LongStream.concat(
                    afterBlinks(parseLong(rockDigits.substring(0, rockDigits.length() / 2)), remainingBlinks),
                    afterBlinks(parseLong(rockDigits.substring(rockDigits.length() / 2)), remainingBlinks)
            );
        }

        return afterBlinks(rock * 2024, remainingBlinks);
    }
}
