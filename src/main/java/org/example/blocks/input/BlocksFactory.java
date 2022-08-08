package org.example.blocks.input;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

public class BlocksFactory {

    private int blockId = 0;

    /**
     * Factory to create blocks using the given 3 integers.
     */
    public Block createBlock(String text) {
        String[] values = text.split(",");
        checkArgument(values.length == 3, "Please enter three integers separated by ',' in each line");

        List<Integer> dimensions = Arrays.stream(values).map(Integer::parseInt).collect(Collectors.toList());
        return new Block(String.valueOf(blockId++),
                dimensions.get(0),
                dimensions.get(1),
                dimensions.get(2));
    }
}
