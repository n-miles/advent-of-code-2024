package aoc;

import static aoc.Utils.allPoints;
import static aoc.Utils.charAt;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Day10 implements Day {

    @Override
    public long part1(List<String> input) {
        return allPoints(input)
                .filter(point -> charAt(input, point) == '0')
                .mapToLong(point -> trailheadsFrom(input, point).left().size())
                .sum();
    }

    @Override
    public long part2(List<String> input) {
        return allPoints(input)
                .filter(point -> charAt(input, point) == '0')
                .mapToLong(point -> trailheadsFrom(input, point).right())
                .sum();
    }

    private Pair<Set<Point>, Long> trailheadsFrom(List<String> input, Point point) {
        char here = charAt(input, point);
        if (here == '9') {
            return new Pair<>(Set.of(point), 1L);
        }
        return Stream.of(
                        new Point(point.x() + 1, point.y()),
                        new Point(point.x() - 1, point.y()),
                        new Point(point.x(), point.y() + 1),
                        new Point(point.x(), point.y() - 1)
                ).filter(next -> {
                    try {
                        return charAt(input, next) == here + 1;
                    } catch (Exception _) {
                        return false;
                    }
                })
                .map(next -> trailheadsFrom(input, next))
                .reduce((a, b) -> {
                    var endings = new HashSet<>(a.left());
                    endings.addAll(b.left());
                    return new Pair<>(endings, a.right() + b.right());
                }).orElseGet(() -> new Pair<>(Set.of(), 0L));
    }
}
