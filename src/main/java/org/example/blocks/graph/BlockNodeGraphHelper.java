package org.example.blocks.graph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNull;

public class BlockNodeGraphHelper {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final BlockNodeComparator blockNodeComparator;

    public BlockNodeGraphHelper(BlockNodeComparator blockNodeComparator) {
        this.blockNodeComparator = requireNonNull(blockNodeComparator);
    }

    public void addAsChildNode(BlockNode parentNode, BlockNode newNode) {
        // verify that childNode dimensions are bigger than parent node.

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
        for (BlockNode childNode : parentNode.getChildNodes()) {
            if (childNode.getBlockId().equals(newNode.getBlockId())) {
                logger.debug("ParentNode={} and childNode={} has same ids", parentNode, newNode);
                continue;
            }

            // if the new node can be a parent of the existing child,
            if (blockNodeComparator.isValidChild(newNode, childNode)) {
                logger.info("Adding  " + newNode + " as parent of " + childNode);

                addAsParentNode(childNode, newNode);

                parentNode.removeChildNode(childNode);
                parentNode.addChildNode(newNode);
                isNodeAdded = true;
            } else if (blockNodeComparator.isValidChild(childNode, newNode)) {
                logger.info("Adding  " + newNode + " as child of " + childNode);
                addAsChildNode(childNode, newNode);
                isNodeAdded = true;
            }
        }

        if (!isNodeAdded) {
            parentNode.addChildNode(newNode);
        }

        BlockNode maxHeightChild = blockNodeComparator.findMaxHeightNode(parentNode.getChildNodes());
        if (maxHeightChild != null) {
            parentNode.updateMaxChildNode(maxHeightChild);
        }
    }

    public void addAsParentNode(BlockNode node, BlockNode newNode) {
        newNode.addChildNode(node);
        if (newNode.getMaxChildStackHeight() < node.getStackHeight()) {
            newNode.updateMaxChildNode(node);
        }
    }
}
