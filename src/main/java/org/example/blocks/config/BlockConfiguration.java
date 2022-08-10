package org.example.blocks.config;

import org.example.blocks.BlockPuzzleRunner;
import org.example.blocks.graph.BlockNodeComparator;
import org.example.blocks.graph.BlockNodeFactory;
import org.example.blocks.graph.BlockNodeGraphHelper;
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
    public BlockPuzzleRunner blockOrganizer(BlocksReader blocksReader,
                                            BlockNodeFactory blockNodeFactory,
                                            BlockNodeGraphHelper blockNodeGraphHelper) {
        return new BlockPuzzleRunner(blocksReader, blockNodeFactory, blockNodeGraphHelper);
    }
}
