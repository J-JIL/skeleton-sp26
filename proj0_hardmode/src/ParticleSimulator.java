import edu.princeton.cs.algs4.StdDraw;
import java.util.Map;

public class ParticleSimulator {
    public Particle[][] particles;
    public int width;
    public int height;
    public static final Map<Character, ParticleFlavor> LETTER_TO_PARTICLE = Map.of(
            's', ParticleFlavor.SAND,
            'b', ParticleFlavor.BARRIER,
            'w', ParticleFlavor.WATER,
            'p', ParticleFlavor.PLANT,
            'f', ParticleFlavor.FIRE,
            '.', ParticleFlavor.EMPTY,
            'n', ParticleFlavor.FOUNTAIN,
            'r', ParticleFlavor.FLOWER
    );

    public ParticleSimulator(int w,int h){
        width = w;
        height = h;
        particles = new Particle[w][h];
        for(int i=0;i<w;i++){
            for(int j=0;j<h;j++){
                particles[i][j] = new Particle(ParticleFlavor.EMPTY);
            }
        }
    }

    // task 5 finished

    public void drawParticles() {
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                StdDraw.setPenColor(particles[x][y].color());
                StdDraw.filledSquare(x, y, 0.5);
            }
        }
    }
    public static void main(String[] args) {
        ParticleSimulator particleSimulator = new ParticleSimulator(150, 150);
        StdDraw.setXscale(0, particleSimulator.width);
        StdDraw.setYscale(0, particleSimulator.height);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        ParticleFlavor nextParticleFlavor = ParticleFlavor.SAND;

        while (true) {
            if(StdDraw.hasNextKeyTyped()){
                nextParticleFlavor = LETTER_TO_PARTICLE.get(StdDraw.nextKeyTyped());
            }
            if (StdDraw.isMousePressed()) {
                int x = (int) StdDraw.mouseX();
                int y = (int) StdDraw.mouseY();
                if(particleSimulator.validIndex(x,y)) {
                    particleSimulator.particles[x][y] = new Particle(nextParticleFlavor);
                }
            }

            particleSimulator.drawParticles();
            StdDraw.show();
            StdDraw.pause(5);
        }
    }

    // task 6 finished
    // task 7 finished   fix the main method

    public boolean validIndex(int x, int y){
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    // task 8 finished
    // task 9 finished   fix the main method

    public Map<Direction, Particle> getNeighbors(int x, int y){
        return Map.of(
                Direction.UP,y<height-1?particles[x][y+1]:new Particle(ParticleFlavor.BARRIER),
                Direction.DOWN,y>0?particles[x][y-1]:new Particle(ParticleFlavor.BARRIER),
                Direction.LEFT,x>0?particles[x-1][y]:new Particle(ParticleFlavor.BARRIER),
                Direction.RIGHT,x<width-1?particles[x+1][y]:new Particle(ParticleFlavor.BARRIER)
                );
    }

    // task 10 finished

    
}

