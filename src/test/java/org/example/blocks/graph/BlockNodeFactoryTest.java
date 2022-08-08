package org.example.blocks.graph;

import org.example.blocks.input.Block;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for {@link BlockNodeFactory}
 */
public class BlockNodeFactoryTest {

    private BlockNodeFactory blockNodeFactory;

    @Before
    public void init() {
        blockNodeFactory = new BlockNodeFactory();
    }

    @Test
    public void testGenerate_WhenAllSameSize() {
        // Given
        Block block = new Block("Id1", 1, 1,1);

        // When
        List<BlockNode> blockGraphNodeList = blockNodeFactory.create(block);

        // Then
        assertThat(blockGraphNodeList.size(), is(1));
        assertBlock(blockGraphNodeList.get(0), "Id1", 1, 1, 1);
    }

    @Test
    public void testGenerate_WhenHeightAndWidthSame() {
        // Given
        Block block = new Block("Id1", 1, 2,1);

        // When
        List<BlockNode> blockNodeList = blockNodeFactory.create(block);

        // Then
        assertThat(blockNodeList.size(), is(2));
        assertBlock(blockNodeList.get(0), "Id1", 1, 1, 2);
        assertBlock(blockNodeList.get(1), "Id1", 1, 2, 1);
    }

    @Test
    public void testGenerate_WhenWidthAndLengthSameSame() {
        // Given
        Block block = new Block("Id1", 2, 2,1);

        // When
        List<BlockNode> blockNodeList = blockNodeFactory.create(block);

        // Then
        assertThat(blockNodeList.size(), is(2));
        assertBlock(blockNodeList.get(0), "Id1", 1, 2, 2);
        assertBlock(blockNodeList.get(1), "Id1", 2, 2, 1);
    }

    @Test
    public void testGenerate_WhenHeightAndLengthSameSame() {
        // Given
        Block block = new Block("Id1", 1, 2,2);

        // When
        List<BlockNode> blockNodeList = blockNodeFactory.create(block);

        // Then
        assertThat(blockNodeList.size(), is(2));
        assertBlock(blockNodeList.get(0), "Id1", 1, 2, 2);
        assertBlock(blockNodeList.get(1), "Id1", 2, 2, 1);
    }

    @Test
    public void testGenerate_WhenDistinctDimensions() {
        // Given
        Block block = new Block("Id1", 1, 2,3);

        // When
        List<BlockNode> blockNodeList = blockNodeFactory.create(block);

        // Then
        assertThat(blockNodeList.size(), is(3));
        assertBlock(blockNodeList.get(0), "Id1", 1, 2, 3);
        assertBlock(blockNodeList.get(1), "Id1", 1, 3, 2);
        assertBlock(blockNodeList.get(2), "Id1", 2, 3, 1);
    }

    private void assertBlock(BlockNode blockNode, String id, int width, int length, int height) {
        assertThat(blockNode.getBlockId(), is (id));
        assertThat(blockNode.getWidth(), is(width));
        assertThat(blockNode.getLength(), is (length));
        assertThat(blockNode.getHeight(), is (height));
        assertThat(blockNode.getChildNodes(), is(Collections.EMPTY_SET));
    }
}
