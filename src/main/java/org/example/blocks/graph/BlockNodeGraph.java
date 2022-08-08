package org.example.blocks.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;

public class BlockNodeGraph {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Set<BlockNode> rootNodes = new HashSet<>();
    private BlockNode maxHeightChild = null;

    private final BlockNodeComparator blockNodeComparator;
    private final BlockNodeGraphHelper blockNodeGraphHelper;

    public BlockNodeGraph(BlockNodeComparator blockNodeComparator, BlockNodeGraphHelper blockNodeGraphHelper) {
        this.blockNodeComparator = requireNonNull(blockNodeComparator);
        this.blockNodeGraphHelper = requireNonNull(blockNodeGraphHelper);
    }

    public void addNode(BlockNode newNode) {

        //
        // for each child node
        //      If newChild id is same as existingChild, skip.
        //      if newChild can be parent for any of the existingChild, add that existingChild as child for newChild
        //        And remove existingChild from childrenList and add new node.
        //        (above check should include identical sizes as well)
        //      else if newChild can be child of existing child, invoke addAsChildNode recursively.
        //      else add newChild.
        //
        // Update maxHeight and maxHeightChild
        //
        boolean isNodeAdded = false;
        for (BlockNode rootNode : Set.copyOf(rootNodes)) {
            if (rootNode.getBlockId().equals(newNode.getBlockId())) {
                continue;
            }
            // if the new node can be a parent of the existing child,
            if (blockNodeComparator.isValidChild(newNode, rootNode)) {
                logger.info("Adding  " + newNode + " as parent of " + rootNode);
                blockNodeGraphHelper.addAsParentNode(rootNode, newNode);
                rootNodes.remove(rootNode);
                rootNodes.add(newNode);
                isNodeAdded = true;
            } else if (blockNodeComparator.isValidChild(rootNode, newNode)) {
                logger.info("Adding  " + newNode + " as child of " + rootNode);
                blockNodeGraphHelper.addAsChildNode(rootNode, newNode);
                isNodeAdded = true;
            }
        }
        if (!isNodeAdded) {
            rootNodes.add(newNode);
        }

        maxHeightChild = blockNodeComparator.findMaxHeightNode(rootNodes);
        logger.info("RootNodes " + rootNodes + " - MaxHeightChild " + maxHeightChild + " Height " + maxHeightChild.getStackHeight());
    }

    public BlockNode getMaxHeightChild() {
        return maxHeightChild;
    }
}
