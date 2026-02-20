import java.awt.*;
import java.util.Map;

public class Particle {
    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;
    public static final Map<ParticleFlavor, Integer> LIFESPANS =
            Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
                    ParticleFlavor.PLANT, PLANT_LIFESPAN,
                    ParticleFlavor.FIRE, FIRE_LIFESPAN);

    // task1 finished

    ParticleFlavor flavor;
    int lifespan;
    public Particle(ParticleFlavor flavor){
        this.flavor = flavor;
        this.lifespan = LIFESPANS.getOrDefault(flavor,-1);
    }

    // task2 finished

    public Color color(){
        Map<ParticleFlavor, Color> colorMap =
                Map.of( ParticleFlavor.EMPTY,Color.BLACK,
                        ParticleFlavor.SAND,Color.YELLOW,
                        ParticleFlavor.BARRIER,Color.GRAY,
                        ParticleFlavor.WATER,Color.BLUE,
                        ParticleFlavor.FOUNTAIN,Color.CYAN,
                        ParticleFlavor.PLANT,new Color(0, 255, 0),
                        ParticleFlavor.FIRE,new Color(255,0,0),
                        ParticleFlavor.FLOWER,new Color(255,141,161));
        return colorMap.get(flavor);
    }

    // task 3 finished

    public void moveInto(Particle other){
        other.flavor = flavor;
        other.lifespan = lifespan;
        flavor = ParticleFlavor.EMPTY;
        lifespan = -1;
    }

    //task 4 finished

    public void fall(Map<Direction, Particle> neighbors){
        if(neighbors.get(Direction.DOWN).flavor == ParticleFlavor.EMPTY){
            neighbors.get(Direction.DOWN).flavor = flavor;
            neighbors.get(Direction.DOWN).lifespan = lifespan;
            flavor = ParticleFlavor.EMPTY;
            lifespan = -1;
        }
    }

    // test 11 finished


}
