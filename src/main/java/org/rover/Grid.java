package org.rover;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.rover.Direction.*;

public class Grid {
    private int MAX_HEIGHT = 10;
    private int MAX_WIDTH = 10;
    private List<Position> obstacles = Collections.emptyList();

    public Grid(int width, int height) {
        this.MAX_WIDTH = width;
        this.MAX_HEIGHT = height;
    }

    public Grid(List<Position> obstacles){
        this.obstacles = obstacles;
    }


    Optional<Position> nextPositionFor(Position position, Direction direction) {
        int y = position.y();
        int x = position.x();
        if (direction == NORTH) {
            y = (y + 1) % MAX_HEIGHT;
        }
        if (direction == EAST) {
            x = (x + 1) % MAX_WIDTH;
        }
        if (direction == WEST) {
            x = (x > 0) ? x - 1 : MAX_HEIGHT - 1;
        }
        if (direction == SOUTH) {
            y = (y > 0) ? y - 1 : MAX_HEIGHT - 1;
        }
        Position newPosition = new Position(x,y);
        return obstacles.contains(newPosition) ? Optional.empty() : Optional.of(newPosition);
    }
}
