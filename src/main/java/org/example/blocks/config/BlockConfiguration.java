package org.example.blocks.config;

import org.example.blocks.graph.StackableBlocksGraphBuilder;
import org.example.blocks.graph.StackableBlocksGraphTraverser;
import org.example.blocks.input.BlockPermutationsGenerator;
import org.example.blocks.BlockPuzzleRunner;
import org.example.blocks.input.BlocksFactory;
import org.example.blocks.input.BlocksReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlockConfiguration {

    @Bean
    public BlocksFactory blocksFactory() {
        return new BlocksFactory();
    }

    @Bean
    public BlocksReader blocksReader(BlocksFactory blocksFactory) {
        return new BlocksReader(blocksFactory);
    }

    @Bean
    public BlockPermutationsGenerator blockPermutationsGenerator() {
        return new BlockPermutationsGenerator();
    }

    @Bean
    public StackableBlocksGraphBuilder stackableBlocksGraphBuilder(BlockPermutationsGenerator blockPermutationsGenerator) {
        return new StackableBlocksGraphBuilder(blockPermutationsGenerator);
    }

    @Bean
    public StackableBlocksGraphTraverser stackableBlocksGraphTraverser() {
        return new StackableBlocksGraphTraverser();
    }

    @Bean
    public BlockPuzzleRunner blockOrganizer(BlocksReader blocksReader,
                                            StackableBlocksGraphBuilder stackableBlocksGraphBuilder,
                                            StackableBlocksGraphTraverser stackableBlocksGraphTraverser) {
        return new BlockPuzzleRunner(blocksReader, stackableBlocksGraphBuilder, stackableBlocksGraphTraverser);
    }
}
