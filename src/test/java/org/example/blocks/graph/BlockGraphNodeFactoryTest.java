package org.example.blocks.graph;

import org.example.blocks.input.Block;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for {@link BlockGraphNodeFactory}
 */
public class BlockGraphNodeFactoryTest {

    private BlockGraphNodeFactory blockGraphNodeFactory;

    @Before
    public void init() {
        blockGraphNodeFactory = new BlockGraphNodeFactory();
    }

    @Test
    public void testGenerate_WhenAllSameSize() {
        // Given
        Block block = new Block("Id1", 1, 1,1);

        // When
        List<BlockGraphNode> blockGraphNodeList = blockGraphNodeFactory.create(block);

        // Then
        assertThat(blockGraphNodeList.size(), is(1));
        assertBlock(blockGraphNodeList.get(0), "Id1", 1, 1, 1);
    }

    @Test
    public void testGenerate_WhenHeightAndWidthSame() {
        // Given
        Block block = new Block("Id1", 1, 2,1);

        // When
        List<BlockGraphNode> blockGraphNodeList = blockGraphNodeFactory.create(block);

        // Then
        assertThat(blockGraphNodeList.size(), is(2));
        assertBlock(blockGraphNodeList.get(0), "Id1", 1, 1, 2);
        assertBlock(blockGraphNodeList.get(1), "Id1", 1, 2, 1);
    }

    @Test
    public void testGenerate_WhenWidthAndLengthSameSame() {
        // Given
        Block block = new Block("Id1", 2, 2,1);

        // When
        List<BlockGraphNode> blockGraphNodeList = blockGraphNodeFactory.create(block);

        // Then
        assertThat(blockGraphNodeList.size(), is(2));
        assertBlock(blockGraphNodeList.get(0), "Id1", 1, 2, 2);
        assertBlock(blockGraphNodeList.get(1), "Id1", 2, 2, 1);
    }

    @Test
    public void testGenerate_WhenHeightAndLengthSameSame() {
        // Given
        Block block = new Block("Id1", 1, 2,2);

        // When
        List<BlockGraphNode> blockGraphNodeList = blockGraphNodeFactory.create(block);

        // Then
        assertThat(blockGraphNodeList.size(), is(2));
        assertBlock(blockGraphNodeList.get(0), "Id1", 1, 2, 2);
        assertBlock(blockGraphNodeList.get(1), "Id1", 2, 2, 1);
    }

    @Test
    public void testGenerate_WhenDistinctDimensions() {
        // Given
        Block block = new Block("Id1", 1, 2,3);

        // When
        List<BlockGraphNode> blockGraphNodeList = blockGraphNodeFactory.create(block);

        // Then
        assertThat(blockGraphNodeList.size(), is(3));
        assertBlock(blockGraphNodeList.get(0), "Id1", 1, 2, 3);
        assertBlock(blockGraphNodeList.get(1), "Id1", 1, 3, 2);
        assertBlock(blockGraphNodeList.get(2), "Id1", 2, 3, 1);
    }

    private void assertBlock(BlockGraphNode blockGraphNode, String id, int width, int length, int height) {
        assertThat(blockGraphNode.getBlockId(), is (id));
        assertThat(blockGraphNode.getWidth(), is(width));
        assertThat(blockGraphNode.getLength(), is (length));
        assertThat(blockGraphNode.getHeight(), is (height));
        assertThat(blockGraphNode.getChildNodes(), is(Collections.EMPTY_LIST));
    }
}
