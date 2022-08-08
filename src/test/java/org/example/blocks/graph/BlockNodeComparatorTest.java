package org.example.blocks.graph;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test fpr {@link BlockNodeComparator}
 */
public class BlockNodeComparatorTest {
    private BlockNodeComparator blockNodeComparator;

    @Before
    public void init() {
        blockNodeComparator = new BlockNodeComparator();
    }

    @Test
    public void testIsValidChild_WhenParentIsSmaller() {
        // Given
        BlockNode parent = new BlockNode("Id", 1, 3, 4);
        BlockNode child = new BlockNode("Id", 1, 4, 5);

        // When
        boolean isValidChild = blockNodeComparator.isValidChild(parent, child);

        // Then
        assertThat(isValidChild, is(true));
    }

    @Test
    public void testIsValidChild_WhenParentIsNotSmaller() {
        // Given
        BlockNode parent = new BlockNode("Id", 1, 4, 4);
        BlockNode child = new BlockNode("Id", 1, 3, 5);

        // When
        boolean isValidChild = blockNodeComparator.isValidChild(parent, child);

        // Then
        assertThat(isValidChild, is(false));
    }

    @Test
    public void testIsValidChild_WhenParentIsSameAsChild() {
        // Given
        BlockNode parent = new BlockNode("Id", 1, 4, 4);
        BlockNode child = new BlockNode("Id", 1, 4, 4);

        // When
        boolean isValidChild = blockNodeComparator.isValidChild(parent, child);

        // Then
        assertThat(isValidChild, is(true));
    }

    @Test
    public void testFindMaxHeightNode() {
        // Given
        BlockNode blockNode1 = mock(BlockNode.class);
        BlockNode blockNode2 = mock(BlockNode.class);
        when(blockNode1.getStackHeight()).thenReturn(10);
        when(blockNode2.getStackHeight()).thenReturn(20);

        Set<BlockNode> blockNodeSet = Sets.newSet(blockNode1, blockNode2);

        // When
        BlockNode maxHeightBlock = blockNodeComparator.findMaxHeightNode(blockNodeSet);

        // Then
        assertThat(maxHeightBlock, is(blockNode2));
    }
}
