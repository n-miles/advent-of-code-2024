package aoc;

import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.stream.Stream;

public class Utils {

    public static Stream<Point> allPoints(int width, int height) {
        return range(0, width).boxed()
                .flatMap(x -> range(0, height).mapToObj(y -> new Point(x, y)));
    }

    public static <T> Stream<Pair<T, T>> allCombinationsUnordered(List<T> items) {
        return range(0, items.size() - 1).boxed()
                .flatMap(i -> range(i + 1, items.size())
                        .mapToObj(j -> new Pair<>(items.get(i), items.get(j)))
                );
    }
}
