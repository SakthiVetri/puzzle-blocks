package org.example.block.graph;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit test for {@link PathLengthCalculator}
 */
public class PathLengthCalculatorTest {

    private PathLengthCalculator pathLengthCalculator;

    @Before
    public void init() {
        pathLengthCalculator = new PathLengthCalculator();
    }

    @Test
    public void testCalculateLength_WhenNoElements() {
        // Given
        List<BlockGraphNode> path = new ArrayList<>();

        // When
        int length = pathLengthCalculator.calculateLength(path);

        // Then
        assertThat(length, is(0));
    }

    @Test
    public void testCalculateLength() {
        // Given
        BlockGraphNode blockGraphNode1 = mock(BlockGraphNode.class);
        when(blockGraphNode1.getSameSizeNodeCount()).thenReturn(2);
        when(blockGraphNode1.getHeight()).thenReturn(5);

        BlockGraphNode blockGraphNode2 = mock(BlockGraphNode.class);
        when(blockGraphNode2.getSameSizeNodeCount()).thenReturn(1);
        when(blockGraphNode2.getHeight()).thenReturn(10);

        List<BlockGraphNode> path = Lists.newArrayList(blockGraphNode1, blockGraphNode2);

        // When
        int length = pathLengthCalculator.calculateLength(path);

        // Then  5*2 + 10
        assertThat(length, is(20));
    }
}
