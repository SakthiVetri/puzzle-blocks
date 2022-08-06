package org.example.blocks.graph;

import org.example.blocks.Block;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Unit test for {@link StackableBlocksGraph}
 */
public class StackableBlocksGraphTest {
    private StackableBlocksGraph stackableBlocksGraph;

    @Before
    public void init() {
        stackableBlocksGraph = new StackableBlocksGraph();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGraph_WhenSameNodeAdded() {
        //  Given
        Block block1 = new Block("Id1", 1, 1, 1);
        Block block2 = new Block("Id1", 1, 1, 1);

        stackableBlocksGraph.addNode(block1);

        // When
        stackableBlocksGraph.addNode(block2);

        // Then IllegalArgumentException is thrown
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGraph_WhenEdgeWithoutNode() {
        //  Given
        Block from = new Block("Id1", 1, 1, 1);
        Block to = new Block("Id1", 1, 1, 1);

        // When
        stackableBlocksGraph.addEdge(from, to);

        // Then IllegalArgumentException is thrown
    }

    @Test
    public void testGraph_WhenOnlyNodes() {
        //  Given
        Block block1 = new Block("Id1", 1, 1, 1);
        Block block2 = new Block("Id2", 1, 1, 1);

        // When
        stackableBlocksGraph.addNode(block1);
        stackableBlocksGraph.addNode(block2);

        // Then
        assertThat(stackableBlocksGraph.getRootBlocks(), containsInAnyOrder(block1, block2));
        assertThat(stackableBlocksGraph.getAdjacentBlocks(block1), empty());
        assertThat(stackableBlocksGraph.getAdjacentBlocks(block2), empty());
    }

    @Test
    public void testGraph() {
        //  Given
        Block block1 = new Block("Id1", 1, 1, 1);
        Block block2 = new Block("Id2", 1, 1, 1);
        Block block3 = new Block("Id3", 1, 1, 1);

        // When
        stackableBlocksGraph.addNode(block1);
        stackableBlocksGraph.addNode(block2);
        stackableBlocksGraph.addEdge(block1, block2);

        stackableBlocksGraph.addNode(block3);
        stackableBlocksGraph.addEdge(block1, block3);

        // Then
        assertThat(stackableBlocksGraph.getRootBlocks(), containsInAnyOrder(block1));
        assertThat(stackableBlocksGraph.getAdjacentBlocks(block1), containsInAnyOrder(block2, block3));
        assertThat(stackableBlocksGraph.getAdjacentBlocks(block2), empty());
        assertThat(stackableBlocksGraph.getAdjacentBlocks(block3), empty());
    }
}
