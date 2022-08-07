package org.example.blocks.input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class BlocksReader {
    private static final String EXIT = "exit";

    private final BlocksFactory blocksFactory;

    public BlocksReader(BlocksFactory blocksFactory) {
        this.blocksFactory = requireNonNull(blocksFactory);
    }


    public List<Block> readBlocks(InputStream inputStream) throws IOException {

        List<Block> blockList = new ArrayList<>();
        BufferedReader  bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            if (EXIT.equalsIgnoreCase(line)) {
                break;
            }
            blockList.add(blocksFactory.createBlock(line));
        }
        return blockList;
    }
}
