package org.example.block.graph;

import java.util.List;

public class PathLengthCalculator {

    public int calculateLength(List<BlockGraphNode> blockGraphNodes) {
        int height = 0;
        for (BlockGraphNode blockGraphNode : blockGraphNodes) {
            height = height + (blockGraphNode.getSameSizeNodeCount() * blockGraphNode.getHeight());
        }
        return height;
    }
}
