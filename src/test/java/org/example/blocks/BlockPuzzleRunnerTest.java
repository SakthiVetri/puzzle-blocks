package org.example.blocks;

import com.google.common.collect.Lists;
import org.example.blocks.graph.*;
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
        LongestPathTraverser longestPathTraverser = new LongestPathTraverser(blockGraphTraverser, new PathLengthCalculator());
        blockPuzzleRunner = new BlockPuzzleRunner(blocksReader, blockGraphBuilder, longestPathTraverser);
    }

    @Test
    public void test_case1() throws IOException {
        List<Block> blocksList = Lists.newArrayList(
                new Block("B1",  50, 45,20),
                new Block("B2",  95,37, 53),
                new Block("B3",  45,23, 12));
        when(blocksReader.readBlocks(any())).thenReturn(blocksList);

        // When
        int length = blockPuzzleRunner.run();

        // Then
        assertThat(length, is(190));
    }

    @Test
    public void test_case2() throws IOException {
        List<Block> blocksList = Lists.newArrayList(
                new Block("B1",  38,25,45),
                new Block("B2",  76,35,3));
        when(blocksReader.readBlocks(any())).thenReturn(blocksList);

        // When
        int length = blockPuzzleRunner.run();

        // Then
        assertThat(length, is(76));
    }

    @Test
    public void test_case3() throws IOException {
        List<Block> blocksList = Lists.newArrayList(
                new Block("B1",  7,11,17),
                new Block("B2",  7,17,11),
                new Block("B3",  11,7,17),
                new Block("B4",  11,17,7),
                new Block("B5",  17,7,11),
                new Block("B6",  17,11,7));
        when(blocksReader.readBlocks(any())).thenReturn(blocksList);

        // When
        int length = blockPuzzleRunner.run();

        // Then
        assertThat(length, is(102));
    }
}
