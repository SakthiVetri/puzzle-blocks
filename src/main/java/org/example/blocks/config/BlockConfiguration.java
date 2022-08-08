package org.example.blocks.config;

import org.example.blocks.BlockPuzzleRunner;
import org.example.blocks.graph3.BlockNodeComparator;
import org.example.blocks.graph3.BlockNodeFactory;
import org.example.blocks.graph3.BlockNodeGraph;
import org.example.blocks.graph3.BlockNodeGraphHelper;
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
    public BlockNodeFactory blockNodeFactory() {
        return new BlockNodeFactory();
    }

    @Bean
    public BlockNodeComparator blockNodeComparator() {
        return new BlockNodeComparator();
    }

    @Bean
    public BlockNodeGraphHelper blockNodeGraphHelper(BlockNodeComparator blockNodeComparator) {
        return new BlockNodeGraphHelper(blockNodeComparator);
    }

    @Bean
    public BlockNodeGraph blockNodeGraph(BlockNodeComparator blockNodeComparator,
                                         BlockNodeGraphHelper blockNodeGraphHelper) {
        return new BlockNodeGraph(blockNodeComparator, blockNodeGraphHelper);
    }

    @Bean
    public BlockPuzzleRunner blockOrganizer(BlocksReader blocksReader,
                                            BlockNodeFactory blockNodeFactory,
                                            BlockNodeGraph blockNodeGraph) {
        return new BlockPuzzleRunner(blocksReader, blockNodeFactory, blockNodeGraph);
    }
}
