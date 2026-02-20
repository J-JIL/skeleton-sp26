import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

import org.junit.Test;
import java.util.Map;

public class TestParticleSimulator {

    @Test
    public void testConstructor_initializesEmptyGrid_usingIndices() {
        int w = 50;
        int h = 60;
        ParticleSimulator simulator = new ParticleSimulator(w, h);

        // 1. Verify the outer array length (Width)
        assertThat(simulator.particles).hasLength(w);

        // 2. Iterate using Integer Indices
        for (int x = 0; x < w; x++) {

            // Verify the inner array length (Height) for this column
            assertThat(simulator.particles[x]).hasLength(h);

            for (int y = 0; y < simulator.height; y++) {
                Particle particle = simulator.particles[x][y];

                // Verify the particle is not null
                assertThat(particle).isNotNull();

                // Verify the particle is initialized to EMPTY
                assertWithMessage("Particle at x=%s, y=%s should be EMPTY", x, y)
                        .that(particle.flavor)
                        .isEqualTo(ParticleFlavor.EMPTY);
            }
        }
    }
    @Test
    public void testValidIndex() {
        // Arrange: Create a grid of 10x20
        int width = 10;
        int height = 20;
        ParticleSimulator sim = new ParticleSimulator(width, height);

        // Assert: Valid Indices (Inside bounds)
        assertThat(sim.validIndex(0, 0)).isTrue();             // Bottom-Left Corner
        assertThat(sim.validIndex(9, 19)).isTrue();            // Top-Right Corner (width-1, height-1)
        assertThat(sim.validIndex(5, 10)).isTrue();            // Middle

        // Assert: Invalid Indices (Outside bounds)
        assertThat(sim.validIndex(-1, 0)).isFalse();           // Negative X
        assertThat(sim.validIndex(0, -1)).isFalse();           // Negative Y
        assertThat(sim.validIndex(10, 0)).isFalse();           // X == Width (Off by one)
        assertThat(sim.validIndex(0, 20)).isFalse();           // Y == Height (Off by one)
        assertThat(sim.validIndex(100, 100)).isFalse();        // Far out of bounds
    }
    @Test
    public void testGetNeighbors() {
        // Arrange: Create a small 3x3 grid
        // (0,2) (1,2) (2,2)
        // (0,1) (1,1) (2,1)
        // (0,0) (1,0) (2,0)
        ParticleSimulator sim = new ParticleSimulator(3, 3);

        // Setup specific particles around the center (1,1) to verify correct mapping
        sim.particles[1][2] = new Particle(ParticleFlavor.WATER); // UP of center
        sim.particles[1][0] = new Particle(ParticleFlavor.SAND);  // DOWN of center
        sim.particles[0][1] = new Particle(ParticleFlavor.FIRE);  // LEFT of center
        sim.particles[2][1] = new Particle(ParticleFlavor.PLANT); // RIGHT of center

        // --- Case 1: Center Particle (All neighbors are within bounds) ---
        Map<Direction, Particle> centerNeighbors = sim.getNeighbors(1, 1);

        assertThat(centerNeighbors.get(Direction.UP).flavor).isEqualTo(ParticleFlavor.WATER);
        assertThat(centerNeighbors.get(Direction.DOWN).flavor).isEqualTo(ParticleFlavor.SAND);
        assertThat(centerNeighbors.get(Direction.LEFT).flavor).isEqualTo(ParticleFlavor.FIRE);
        assertThat(centerNeighbors.get(Direction.RIGHT).flavor).isEqualTo(ParticleFlavor.PLANT);

        // --- Case 2: Bottom-Left Corner (0,0) (Verify Off-Screen is Barrier) ---
        // Neighbors for (0,0):
        // UP: (0,1) -> Fire (from setup above)
        // RIGHT: (1,0) -> Sand (from setup above)
        // DOWN: (0,-1) -> Off screen -> Should be BARRIER
        // LEFT: (-1,0) -> Off screen -> Should be BARRIER

        Map<Direction, Particle> cornerNeighbors = sim.getNeighbors(0, 0);

        // Verify valid neighbors
        assertThat(cornerNeighbors.get(Direction.UP).flavor).isEqualTo(ParticleFlavor.FIRE);
        assertThat(cornerNeighbors.get(Direction.RIGHT).flavor).isEqualTo(ParticleFlavor.SAND);

        // Verify invalid/off-screen neighbors are treated as BARRIER
        assertWithMessage("Off-screen neighbor (Down) should be treated as BARRIER")
                .that(cornerNeighbors.get(Direction.DOWN).flavor).isEqualTo(ParticleFlavor.BARRIER);
        assertWithMessage("Off-screen neighbor (Left) should be treated as BARRIER")
                .that(cornerNeighbors.get(Direction.LEFT).flavor).isEqualTo(ParticleFlavor.BARRIER);
    }
    @Test
    public void testFall() {
        // Arrange: Initialize a small 2x2 simulator
        ParticleSimulator sim = new ParticleSimulator(2, 2);

        // --- Scenario 1: Fall into Empty Space ---
        // Setup: Place SAND at (0, 1) and ensure (0, 0) is EMPTY
        // Note that 0, 0 is the bottom left, and 0, 1 is the top left.
        sim.particles[0][1] = new Particle(ParticleFlavor.SAND);
        sim.particles[0][0] = new Particle(ParticleFlavor.EMPTY);

        // Get real neighbors for the particle at (0, 1)
        Map<Direction, Particle> neighbors1 = sim.getNeighbors(0, 1);

        // Act: Tell the particle at (0, 1) to fall
        sim.particles[0][1].fall(neighbors1);

        // Assert:
        // 1. Old position (0, 1) should now be EMPTY
        assertThat(sim.particles[0][1].flavor).isEqualTo(ParticleFlavor.EMPTY);
        // 2. New position (0, 0) should now be SAND
        assertThat(sim.particles[0][0].flavor).isEqualTo(ParticleFlavor.SAND);


        // --- Scenario 2: Blocked by Barrier ---
        // Setup: Place SAND at (1, 1) and BARRIER at (1, 0)
        sim.particles[1][1] = new Particle(ParticleFlavor.SAND);
        sim.particles[1][0] = new Particle(ParticleFlavor.BARRIER);

        // Get real neighbors for the particle at (1, 1)
        Map<Direction, Particle> neighbors2 = sim.getNeighbors(1, 1);

        // Act: Tell the particle at (1, 1) to fall
        sim.particles[1][1].fall(neighbors2);

        // Assert:
        // 1. Position (1, 1) stays SAND (blocked)
        assertThat(sim.particles[1][1].flavor).isEqualTo(ParticleFlavor.SAND);
        // 2. Position (1, 0) stays BARRIER
        assertThat(sim.particles[1][0].flavor).isEqualTo(ParticleFlavor.BARRIER);
    }

}
