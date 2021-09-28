package com.company.coffeemakermachine.model;

/**
 * Model class for coffee machine initializer
 */
public class CoffeeMachineInitializer {

    private CoffeeMachine machine;

    /**
     * Default constructor
     */
    public CoffeeMachineInitializer() {
    }

    public CoffeeMachine getMachine() {
        return machine;
    }

    public void setMachine(CoffeeMachine machine) {
        this.machine = machine;
    }
}
