package org.rover;

import java.util.Optional;

public class Rover {


    Direction direction;

    Position position;
    Grid grid;

    public Rover(Grid grid, Direction direction, Position position) {
        this.grid = grid;
        this.direction = direction;
        this.position = position;
    }


    public String execute(String commands) {
        String obstacleStr = "";
        for (char c : commands.toCharArray()) {
            if (c == 'R') {
                direction = direction.right();
            }
            if (c == 'L') {
                direction = direction.left();
            }
            if (c == 'M') {
                obstacleStr = move();
            }
        }
        return obstacleStr + position.x() + ":" + position.y() + ":" + direction.value();
    }

    private String move() {
        Optional<Position> nextPosition;
        nextPosition = grid.nextPositionFor(this.position, direction);
        nextPosition.ifPresent(nc -> this.position = nc);
        return nextPosition.isPresent() ? "" : "0:";
    }


}
