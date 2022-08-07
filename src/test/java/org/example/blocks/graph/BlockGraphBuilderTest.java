package org.example.blocks.graph;

import com.google.common.collect.Lists;
import org.example.blocks.input.Block;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BlockGraphBuilderTest {

    private BlockGraphBuilder blockGraphBuilder;

    @Before
    public void init() {
        blockGraphBuilder = new BlockGraphBuilder(new BlockGraphNodeFactory());
    }

    @Test
    public void testBuildGraph_WhenIdenticalNodes() {
        // Given
        Block block1 = new Block("B1", 1, 2, 3);
        Block block2 = new Block("B2", 1, 3, 2);
        Block block3 = new Block("B3", 3, 2, 1);

        // When
        Set<BlockGraphNode> rootNodes = blockGraphBuilder.buildGraph(Lists.newArrayList(block1, block2, block3));

        // Then
        assertThat(rootNodes.size(), is(3));
    }
}
