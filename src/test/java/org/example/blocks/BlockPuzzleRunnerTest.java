package org.example.blocks;

import com.google.common.collect.Lists;
import org.example.blocks.graph.BlockNodeComparator;
import org.example.blocks.graph.BlockNodeFactory;
import org.example.blocks.graph.BlockNodeGraphHelper;
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
        BlockNodeFactory blockNodeFactory = new BlockNodeFactory();
        BlockNodeComparator blockNodeComparator = new BlockNodeComparator();
        BlockNodeGraphHelper blockNodeGraphHelper = new BlockNodeGraphHelper(blockNodeComparator);
        blockPuzzleRunner = new BlockPuzzleRunner(blocksReader, blockNodeFactory, blockNodeGraphHelper);
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

    @Test
    public void test_case4() throws IOException {
        List<Block> blocksList = Lists.newArrayList(
                new Block("B1",  10, 5,4),
                new Block("B2",  3,4,8),
                new Block("B3",  7,2,3),
                new Block("B4",  6,2, 2),
                new Block("B5",  5,1,1));
        when(blocksReader.readBlocks(any())).thenReturn(blocksList);

        // When
        int length = blockPuzzleRunner.run();

        // Then
        assertThat(length, is(36));
    }
}
