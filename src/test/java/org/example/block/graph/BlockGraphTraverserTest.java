package org.example.block.graph;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BlockGraphTraverserTest {

    public BlockGraphTraverser blockGraphTraverser;

    @Before
    public void init() {
        blockGraphTraverser = new BlockGraphTraverser();
    }

    @Test
    public void testTraverse_WhenNodesOverLap() {
        // Given
        BlockGraphNode rootNode = new BlockGraphNode("B0", 1, 2, 3);

        BlockGraphNode blockGraphNode1 = new BlockGraphNode("B1", 1, 2, 3);
        BlockGraphNode blockGraphNode2 = new BlockGraphNode("B2", 1, 2, 4);
        BlockGraphNode blockGraphNode3 = new BlockGraphNode("B3", 1, 2, 5);
        BlockGraphNode blockGraphNode4 = new BlockGraphNode("B4", 1, 2, 6);

        blockGraphNode1.addChildNode(blockGraphNode2);
        blockGraphNode1.addChildNode(blockGraphNode3);

        blockGraphNode2.addChildNode(blockGraphNode4);

        rootNode.addChildNode(blockGraphNode1);
        rootNode.addChildNode(blockGraphNode3);

        // When
        List<List<BlockGraphNode>> paths = blockGraphTraverser.getAllPaths(rootNode);

        // Then
        // List of paths should be
        //  B0, B1, B2, B4
        //  B0, B1, B3
        //  B0, B3
        assertThat(paths.size(), is(3));
        List<List<String>> pathWithIds = paths.stream()
                .map(path -> path.stream().map(BlockGraphNode::getBlockId).collect(Collectors.toList()))
                .collect(Collectors.toList());

        List<String> expectedPath1 = Lists.newArrayList("B3", "B0");
        List<String> expectedPath2 = Lists.newArrayList("B3", "B1", "B0");
        List<String> expectedPath3 = Lists.newArrayList("B4", "B2", "B1", "B0");
        assertThat(pathWithIds, contains(expectedPath1, expectedPath2, expectedPath3));
    }
}
