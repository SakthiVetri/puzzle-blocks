package org.example.blocks.graph;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
}
