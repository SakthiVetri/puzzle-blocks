package org.example.blocks.graph;

import org.example.blocks.input.Block;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Unit test for {@link BlockNodeGraphHelper}
 */
@RunWith(MockitoJUnitRunner.class)
public class BlockNodeGraphHelperTest {

    private BlockNodeGraphHelper blockNodeGraphHelper;

    @Mock
    private BlockNodeComparator blockNodeComparator;

    @Mock
    private BlockNode parentNode;

    @Mock
    private BlockNode childNode;

    @Before
    public void init() {
        blockNodeGraphHelper = new BlockNodeGraphHelper(blockNodeComparator);
    }

    @After
    public void destroy() {
        verifyNoMoreInteractions(parentNode, childNode);
    }

    @Test
    public void testAddAsParentNode_WhenNewChildHeightIsNotHigher() {
        // Given
        when(parentNode.getMaxChildStackHeight()).thenReturn(10);
        when(childNode.getStackHeight()).thenReturn(8);

        // When
        blockNodeGraphHelper.addAsParentNode(childNode, parentNode);

        // Then
        verify(parentNode).addChildNode(childNode);
        verify(parentNode).getMaxChildStackHeight();
        verify(childNode).getStackHeight();
    }

    @Test
    public void testAddAsParentNode_WhenNewChildHeightIsHigher() {
        // Given
        when(parentNode.getMaxChildStackHeight()).thenReturn(10);
        when(childNode.getStackHeight()).thenReturn(15);

        // When
        blockNodeGraphHelper.addAsParentNode(childNode, parentNode);

        // Then
        verify(parentNode).addChildNode(childNode);
        verify(parentNode).getMaxChildStackHeight();
        verify(childNode).getStackHeight();
        verify(parentNode).updateMaxChildNode(childNode);
    }
}
