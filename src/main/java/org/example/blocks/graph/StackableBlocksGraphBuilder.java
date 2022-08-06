package org.example.blocks.graph;

import org.example.blocks.Block;
import org.example.blocks.input.BlockPermutationsGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.requireNonNull;

public class StackableBlocksGraphBuilder {

    private final BlockPermutationsGenerator blockPermutationsGenerator;

    public StackableBlocksGraphBuilder(BlockPermutationsGenerator blockPermutationsGenerator) {
        this.blockPermutationsGenerator = requireNonNull(blockPermutationsGenerator);
    }

    public StackableBlocksGraph buildGraph(List<Block> blockList) {
        Map<BlockBase, List<Block>> blocksByBase = new HashMap<>();
        StackableBlocksGraph stackableBlocksGraph = new StackableBlocksGraph();

        List<Block> permutedBlocks = new ArrayList<>();
        for (Block block : blockList) {
            List<Block> permutations = blockPermutationsGenerator.generate(block);
            permutedBlocks.addAll(permutations);
        }

//        for (Block block : permutedBlocks) {
//            stackableBlocksGraph.addNode(block);
//
//            BlockBase newBlockBase = new BlockBase(block.getWidth(), block.getLength());
//            for (Map.Entry<BlockBase, List<Block>>  blockBaseEntries : blocksByBase.entrySet()) {
//                if (BlocksComparator.isStackable(blockBaseEntries.getKey(), newBlockBase)) {
//                    for (Block topBlock : blockBaseEntries.getValue()) {
//                        if (topBlock.getHeight() <= block.getHeight()) {
//                            stackableBlocksGraph.addEdge(topBlock, block);
//                        }
//                    }
//                }
//            }
//
//            List<Block> blocks = blocksByBase.get(newBlockBase);
//            if (blocks == null) {
//                blocks = new ArrayList<>();
//                blocksByBase.put(newBlockBase, blocks);
//            }
//            blocks.add(block);
//        }

        for (Block permutedBlock : permutedBlocks) {
            stackableBlocksGraph.addNode(permutedBlock);
        }
        for (int i = 0; i < permutedBlocks.size(); i++) {
            Block source = permutedBlocks.get(i);
            for (int j = 0; j < permutedBlocks.size(); j++) {
                Block destination = permutedBlocks.get(j);
                if (source.getBlockId().equals(destination.getBlockId())) {
                    continue;
                }

                if (BlocksComparator.isStackable(source, destination)) {
                    stackableBlocksGraph.addEdge(source, destination);
                }
            }
        }

        return stackableBlocksGraph;
    }

    static class BlockBase {
        private int width;
        private int length;
        public BlockBase(int width, int length) {
            checkArgument(length >= width, "Length %s should be bigger than width %s", length, width);
            this.width =width;
            this.length = length;
        }

        @Override
        public int hashCode() {
            return width << 8 + length;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (other instanceof  BlockBase) {
                BlockBase otherBlocKbase = (BlockBase) other;
                return width == otherBlocKbase.width &&
                       length == otherBlocKbase.length;
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return width + "_" + length;
        }

        public int getWidth() {
            return width;
        }

        public int getLength() {
            return length;
        }
    }
}
