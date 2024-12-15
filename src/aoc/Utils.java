package aoc;

import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.stream.Stream;

public class Utils {

    public static Stream<Point> allPoints(List<String> strings) {
        return range(0, strings.size()).boxed()
                .flatMap(y -> range(0, strings.get(y).length()).mapToObj(x -> new Point(x, y)));
    }

    public static char charAt(List<String> strings, Point point) {
        return strings.get(point.y()).charAt(point.x());
    }

    public static <T> Stream<Pair<T, T>> allCombinationsUnordered(List<T> items) {
        return range(0, items.size() - 1).boxed()
                .flatMap(i -> range(i + 1, items.size())
                        .mapToObj(j -> new Pair<>(items.get(i), items.get(j)))
                );
    }
}
