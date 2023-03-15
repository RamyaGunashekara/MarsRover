package org.rover;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.rover.Direction.NORTH;

@RunWith(JUnitParamsRunner.class)
public class RoverShould {
    /**
     * always faces 0,0,N.
     */

    private Rover rover;

    @Before
    public void initialise() {
        Grid grid = new Grid(10,10);
        rover = new Rover(grid, NORTH, new Position(0, 0));
    }

    @Test
    @Parameters({
            "R, 0:0:E",
            "RR, 0:0:S",
            "RRR, 0:0:W",
            "RRRR, 0:0:N"
    })
    public void rotate_right(String commands, String position) {
        assertThat(rover.execute(commands), is(position));
    }

    @Test
    @Parameters({
            "L, 0:0:W",
            "LL, 0:0:S",
            "LLL, 0:0:E",
            "LLLL, 0:0:N"
    })
    public void rotate_left(String commands, String position) {
        assertThat(rover.execute(commands), is(position));
    }


    @Test
    @Parameters({
            "M, 0:1:N",
            "MMM, 0:3:N"
    })
    public void move_up(String commands, String position){
        assertThat(rover.execute(commands), is(position));
    }

    @Test
    @Parameters({
            "MMMMMMMMMM, 0:0:N",
            "MMMMMMMMMMMMMMM, 0:5:N"
    })
    public void wrap_from_top_to_bottom_when_moving_north(String commands, String position){
        assertThat(rover.execute(commands), is(position));
    }

    @Test
    @Parameters({
            "RM, 1:0:E",
            "RMMMMM, 5:0:E"
    })
    public void move_right(String commands, String position){
        assertThat(rover.execute(commands), is(position));
    }

    @Test
    @Parameters({
            "RMMMMMMMMMM, 0:0:E",
            "RMMMMMMMMMMMMMMM, 5:0:E"
    })
    public void wrap_from_right_to_left_when_moving_east(String commands, String position){
        assertThat(rover.execute(commands), is(position));
    }

    @Test
    @Parameters({
            "LM, 9:0:W",
            "LMMMMM, 5:0:W"
    })
    public void move_left(String commands, String position){
        assertThat(rover.execute(commands), is(position));
    }

    @Test
    @Parameters({
            "LLM, 0:9:S",
            "LLMMMMM, 0:5:S"
    })
    public void move_south(String commands, String position){
        assertThat(rover.execute(commands), is(position));
    }

    @Test
    @Parameters({
            "MMMM, 0:0:3:N",
            "RMM, 0:1:0:E"
    })
    public void stop_at_obstacle(String commands, String position){
        Position obstacle_0x4 = new Position(0,4);
        Position obstacle_2x0 = new Position(2,0);
        Grid grid = new Grid(Arrays.asList(obstacle_0x4, obstacle_2x0));
        Rover rover = new Rover(grid, NORTH, new Position(0,0));
        assertThat(rover.execute(commands), is(position));
    }

    @Test
    @Parameters({
            "5, 5, 1, 2, NORTH, LMLMLMLMM, 1:3:N",
            "6, 6, 3, 3, EAST, MMRMMRMRRM, 5:1:E"
    })
    public void sampleOutput(int x, int y, int xStart, int yStart, Direction direction, String commands, String position){
        Rover rover = new Rover(new Grid(x,y), direction, new Position(xStart, yStart));
        assertThat(rover.execute(commands), is(position));
    }


}
