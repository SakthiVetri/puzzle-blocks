package org.example.blocks;

import com.google.common.collect.Lists;
import org.example.block.graph.BlockGraphBuilder;
import org.example.block.graph.BlockGraphNodeFactory;
import org.example.block.graph.BlockGraphTraverser;
import org.example.block.graph.LongestPathTraverser;
import org.example.blocks.input.Block;
import org.example.blocks.input.BlocksReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link BlockPuzzleRunner}
 */
@RunWith(MockitoJUnitRunner.class)
public class BlockPuzzleRunnerTest {

    private BlockPuzzleRunner blockPuzzleRunner;

    @Mock
    private BlocksReader blocksReader;

    @Before
    public void init() {
        BlockGraphNodeFactory blockGraphNodeFactory = new BlockGraphNodeFactory();
        BlockGraphBuilder blockGraphBuilder = new BlockGraphBuilder(blockGraphNodeFactory);

        BlockGraphTraverser blockGraphTraverser = new BlockGraphTraverser();
        LongestPathTraverser longestPathTraverser = new LongestPathTraverser(blockGraphTraverser);
        blockPuzzleRunner = new BlockPuzzleRunner(blocksReader, blockGraphBuilder, longestPathTraverser);
    }

    @Test
    public void test_case1() throws IOException {
        List<Block> blocksList = Lists.newArrayList(
                new Block("B1",  45,50, 20),
                new Block("B2",  37,95, 53),
                new Block("B3",  23,45, 12));
        when(blocksReader.readBlocks(any())).thenReturn(blocksList);

        // When
        int length = blockPuzzleRunner.run();

        // Then
        assertThat(length, is(190));
    }
}
