package com.company.coffeemakermachine;

import com.company.coffeemakermachine.model.CoffeeMachineInitializer;
import com.company.coffeemakermachine.util.CoffeeMachineUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Main application class for coffee maker machine
 */
public class CoffeeMakerMachineApplication {

    private static final String COFFEE_INITIALIZER_FILE_PATH = "initialize_coffee_machine.json";

    /**
     * Main class
     * @param args Arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader = CoffeeMakerMachineApplication.class.getClassLoader();
        File file = new File(classLoader.getResource(COFFEE_INITIALIZER_FILE_PATH).getFile());

        String content = new String(Files.readAllBytes(file.toPath()));

        ObjectMapper objectMapper = new ObjectMapper();
        CoffeeMachineInitializer coffeeMachineInitializer = objectMapper.readValue(content, CoffeeMachineInitializer.class);

        CoffeeMachineUtil coffeeMachineUtil = new CoffeeMachineUtil();

        // set the initial ingredients
        coffeeMachineUtil.initialIngredients(coffeeMachineInitializer.getMachine().getTotal_items_quantity());

        // make beverages out of the ingredients present
        coffeeMachineUtil.makeBeverages(coffeeMachineInitializer.getMachine().getBeverages());

    }

}
