package org.example.blocks.input;

import org.example.blocks.Block;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for {@Link BlockPermutationsGenerator}
 */
public class BlockPermutationsGeneratorTest {

    private BlockPermutationsGenerator blockPermutationsGenerator;

    @Before
    public void init() {
        blockPermutationsGenerator = new BlockPermutationsGenerator();
    }

    @Test
    public void testGenerate_WhenAllSameSize() {
        // Given
        Block block = new Block("Id1", 1, 1,1);

        // When
        List<Block> blockPermutation = blockPermutationsGenerator.generate(block);

        // Then
        assertThat(blockPermutation.size(), is(1));
        assertBlock(blockPermutation.get(0), "Id1", 1, 1, 1);
    }

    @Test
    public void testGenerate_WhenHeightAndWidthSame() {
        // Given
        Block block = new Block("Id1", 1, 2,1);

        // When
        List<Block> blockPermutation = blockPermutationsGenerator.generate(block);

        // Then
        assertThat(blockPermutation.size(), is(2));
        assertBlock(blockPermutation.get(0), "Id1", 1, 2, 1);
        assertBlock(blockPermutation.get(1), "Id1", 1, 1, 2);
    }

    @Test
    public void testGenerate_WhenWidthAndLengthSameSame() {
        // Given
        Block block = new Block("Id1", 2, 2,1);

        // When
        List<Block> blockPermutation = blockPermutationsGenerator.generate(block);

        // Then
        assertThat(blockPermutation.size(), is(2));
        assertBlock(blockPermutation.get(0), "Id1", 2, 2, 1);
        assertBlock(blockPermutation.get(1), "Id1", 1, 2, 2);
    }

    @Test
    public void testGenerate_WhenHeightAndLengthSameSame() {
        // Given
        Block block = new Block("Id1", 1, 2,2);

        // When
        List<Block> blockPermutation = blockPermutationsGenerator.generate(block);

        // Then
        assertThat(blockPermutation.size(), is(2));
        assertBlock(blockPermutation.get(0), "Id1", 1, 2, 2);
        assertBlock(blockPermutation.get(1), "Id1", 2, 2, 1);
    }

    @Test
    public void testGenerate_WhenDistinctDimensions() {
        // Given
        Block block = new Block("Id1", 1, 2,3);

        // When
        List<Block> blockPermutation = blockPermutationsGenerator.generate(block);

        // Then
        assertThat(blockPermutation.size(), is(3));
        assertBlock(blockPermutation.get(0), "Id1", 1, 2, 3);
        assertBlock(blockPermutation.get(1), "Id1", 2, 3, 1);
        assertBlock(blockPermutation.get(2), "Id1", 1, 3, 2);
    }

    private void assertBlock(Block block, String id, int width, int length, int height) {
        assertThat(block.getBlockId(), is (id));
        assertThat(block.getWidth(), is(width));
        assertThat(block.getLength(), is (length));
        assertThat(block.getHeight(), is (height));
    }
}
