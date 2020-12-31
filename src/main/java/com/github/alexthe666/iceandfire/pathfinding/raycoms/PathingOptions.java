package com.github.alexthe666.iceandfire.pathfinding.raycoms;
/*
    All of this code is used with permission from Raycoms, one of the developers of the minecolonies project.
 */
public class PathingOptions {
    /**
     * Additional cost of jumping and dropping - base 1.
     */
    public double jumpDropCost = 1.1D;

    /**
     * Cost improvement of paths - base 1.
     */
    public double onPathCost = 0.5D;

    /**
     * Cost improvement of paths - base 1.
     */
    public double onRailCost = 0.1D;

    /**
     * The rails exit cost.
     */
    public double railsExitCost = 2;

    /**
     * Additional cost of swimming - base 1.
     */
    public double swimCost = 1.5D;

    /**
     * Additional cost enter entering water
     */
    public double swimCostEnter = 25D;

    /**
     * Whether to use minecart rail pathing
     */
    private boolean canUseRails = false;
    /**
     * Can swim
     */
    private boolean canSwim = false;
    /**
     * Allowed to enter doors?
     */
    private boolean enterDoors = false;
    /**
     * Allowed to open doors?
     */
    private boolean canOpenDoors = false;

    private boolean canFly = false;

    public PathingOptions() {
    }

    public boolean canOpenDoors() {
        return canOpenDoors;
    }

    public void setCanOpenDoors(final boolean canOpenDoors) {
        this.canOpenDoors = canOpenDoors;
    }

    public boolean canUseRails() {
        return canUseRails;
    }

    public void setCanUseRails(final boolean canUseRails) {
        this.canUseRails = canUseRails;
    }

    public boolean canSwim() {
        return canSwim;
    }

    public void setCanSwim(final boolean canSwim) {
        this.canSwim = canSwim;
    }

    public boolean canEnterDoors() {
        return enterDoors;
    }

    public void setEnterDoors(final boolean enterDoors) {
        this.enterDoors = enterDoors;
    }

    public boolean canFly(){return this.canFly;}

    public void setCanFly(final boolean canFly){this.canFly = canFly;}

    public PathingOptions withStartSwimCost(final double startSwimCost) {
        swimCostEnter = startSwimCost;
        return this;
    }

    public PathingOptions withSwimCost(final double swimCost) {
        this.swimCost = swimCost;
        return this;
    }

    public PathingOptions withJumpDropCost(final double jumpDropCost) {
        this.jumpDropCost = jumpDropCost;
        return this;
    }

    public PathingOptions withOnPathCost(final double onPathCost) {
        this.onPathCost = onPathCost;
        return this;
    }

    public PathingOptions withOnRailCost(final double onRailCost) {
        this.onRailCost = onRailCost;
        return this;
    }

    public PathingOptions withRailExitCost(final double railExitCost) {
        railsExitCost = railExitCost;
        return this;
    }
}