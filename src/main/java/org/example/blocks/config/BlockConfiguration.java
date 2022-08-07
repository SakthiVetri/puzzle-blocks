package org.example.blocks.config;

import org.example.block.graph.BlockGraphBuilder;
import org.example.block.graph.BlockGraphNodeFactory;
import org.example.block.graph.BlockGraphTraverser;
import org.example.block.graph.LongestPathTraverser;
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
    public BlockGraphNodeFactory blockGraphNodeFactory() {
        return new BlockGraphNodeFactory();
    }

    @Bean
    public BlockGraphTraverser blocksGraphTraverser() {
        return new BlockGraphTraverser();
    }

    @Bean
    public BlockGraphBuilder blockGraphBuilder(BlockGraphNodeFactory blockGraphNodeFactory) {
        return new BlockGraphBuilder(blockGraphNodeFactory);
    }

    @Bean
    public LongestPathTraverser longestPathTraverser(BlockGraphTraverser blockGraphTraverser) {
        return new LongestPathTraverser(blockGraphTraverser);
    }

    @Bean
    public BlockPuzzleRunner blockOrganizer(BlocksReader blocksReader,
                                            BlockGraphBuilder blockGraphBuilder,
                                            LongestPathTraverser longestPathTraverser) {
        return new BlockPuzzleRunner(blocksReader, blockGraphBuilder, longestPathTraverser);
    }
}
