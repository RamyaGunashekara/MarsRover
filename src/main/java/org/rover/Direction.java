package org.rover;

enum Direction {
    NORTH("N", "W", "E"),
    EAST("E", "N", "S"),
    SOUTH("S", "E", "W"),
    WEST("W", "S", "N");

    private final String value;
    private final String left;
    private final String right;

    Direction(String value, String left, String right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Direction right() {
        return directionToThe(right);
    }

    private Direction directionToThe(String value) {
        for (Direction direction : values()) {
            if (direction.value.equals(value)) {
                return direction;
            }
        }
        return null;
    }

    public Direction left() {
        return directionToThe(left);
    }

    public String value() {
        return value;
    }
}
