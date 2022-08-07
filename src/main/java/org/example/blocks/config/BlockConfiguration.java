package org.example.blocks.config;

import org.example.block.graph.BlockGraphBuilder;
import org.example.block.graph.BlockGraphNodeFactory;
import org.example.block.graph.BlocksGraphTraverser;
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
    public BlocksGraphTraverser blocksGraphTraverser() {
        return new BlocksGraphTraverser();
    }

    @Bean
    public BlockGraphBuilder blockGraphBuilder(BlockGraphNodeFactory blockGraphNodeFactory) {
        return new BlockGraphBuilder(blockGraphNodeFactory);
    }

    @Bean
    public BlockPuzzleRunner blockOrganizer(BlocksReader blocksReader,
                                            BlockGraphBuilder blockGraphBuilder,
                                            BlocksGraphTraverser blocksGraphTraverser) {
        return new BlockPuzzleRunner(blocksReader, blockGraphBuilder, blocksGraphTraverser);
    }
}
