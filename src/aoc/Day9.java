package aoc;

import java.util.ArrayList;
import java.util.List;

public class Day9 implements Day {

    @Override
    public long part1(List<String> input) {
        List<Integer> disk = parseDisk(input);

        int start = 0;
        int end = disk.size() - 1;
        while (start < end) {
            if (disk.get(start) != null) {
                start++;
                continue;
            }
            if (disk.get(end) == null) {
                end--;
                continue;
            }

            disk.set(start, disk.get(end));
            disk.set(end, null);

            start++;
            end--;
        }

        return checksum(disk);
    }

    @Override
    public long part2(List<String> input) {
        return 0;
    }

    private List<Integer> parseDisk(List<String> input) {
        String initialDisk = input.getFirst();
        List<Integer> disk = new ArrayList<>();

        for (int i = 0; i < initialDisk.length(); i++) {
            int blockSize = initialDisk.charAt(i) - '0';
            for (int j = 0; j < blockSize; j++) {
                if (i % 2 == 0) {
                    disk.add(i / 2);
                } else {
                    disk.add(null);
                }
            }
        }
        return disk;
    }

    private long checksum(List<Integer> disk) {
        long checksum = 0;
        for (int i = 0; i < disk.size(); i++) {
            Integer fileId = disk.get(i);
            if (fileId != null) {
                checksum += i * fileId;
            }
        }
        return checksum;
    }
}
