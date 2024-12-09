package aoc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 implements Day {

    @Override
    public long part1(List<String> input) {
        Dude dude = new Dude(input);
        Set<Point> visited = new HashSet<>();

        try {
            while (true) {
                input.get(dude.position.y()).charAt(dude.position.x());
                visited.add(dude.position);

                int Δx = 0;
                int Δy = 0;
                switch (dude.direction) {
                    case UP -> {
                        Δx = 0;
                        Δy = -1;
                    }
                    case RIGHT -> {
                        Δx = 1;
                        Δy = 0;
                    }
                    case DOWN -> {
                        Δx = 0;
                        Δy = 1;
                    }
                    case LEFT -> {
                        Δx = -1;
                        Δy = 0;
                    }
                }

                Point newPosition = new Point(dude.position.x() + Δx, dude.position.y() + Δy);
                if (input.get(newPosition.y()).charAt(newPosition.x()) == '#') {
                    dude.direction = switch (dude.direction) {
                        case UP -> Direction.RIGHT;
                        case RIGHT -> Direction.DOWN;
                        case DOWN -> Direction.LEFT;
                        case LEFT -> Direction.UP;
                    };
                } else {
                    dude.position = newPosition;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            // lmao
        }

        return visited.size();
    }

    @Override
    public long part2(List<String> input) {
        return 0;
    }

    private enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    private static class Dude {
        Direction direction = Direction.UP;
        Point position;

        Dude(List<String> input) {
            for (int xx = 0; xx < input.getFirst().length(); xx++) {
                for (int yy = 0; yy < input.size(); yy++) {
                    if (input.get(yy).charAt(xx) == '^') {
                        this.position = new Point(xx, yy);
                        return;
                    }
                }
            }
        }
    }
}
