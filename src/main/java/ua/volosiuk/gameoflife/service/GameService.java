package ua.volosiuk.gameoflife.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class GameService {
    private final Random rand = new Random();
    public static final Logger logger = Logger.getLogger(GameService.class.getName());

    // Initialize a random grid of the specified side length
    public List<List<Boolean>> initListList(int sideLength) {
        logger.log(Level.INFO, "service / initListList() started");

        return initializeRandomGrid(sideLength);
    }

    // Calculate the next step
    public List<List<Boolean>> nextStep(List<List<Boolean>> values) {
        logger.log(Level.INFO, "service / nextStep() started");
        int size = values.size();
        List<List<Boolean>> calculatedField = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            List<Boolean> newRow = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                int neighbors = countNeighbors(values, i, j);
                boolean nextElement = determineNextElement(values.get(i).get(j), neighbors);
                newRow.add(nextElement);
            }
            calculatedField.add(newRow);
        }

        return calculatedField;
    }

    // Count the number of neighbors around a given cell
    private int countNeighbors(List<List<Boolean>> values, int row, int col) {
        int size = values.size();
        int neighbors = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) continue;
                int newRow = (row + y + size) % size;
                int newCol = (col + x + size) % size;
                if (Boolean.TRUE.equals(values.get(newRow).get(newCol))) {
                    neighbors++;
                }
            }
        }

        return neighbors;
    }

    // Determine the next state of a cell
    private boolean determineNextElement(boolean currentElement, int neighbors) {
        if (currentElement) {
            return neighbors > 1 && neighbors < 4;
        } else {
            return neighbors == 3;
        }
    }

    // Initialize a grid with all cells set to random boolean values
    private List<List<Boolean>> initializeRandomGrid(int length) {
        logger.log(Level.INFO, "service / initializeRandomGrid() started");
        List<List<Boolean>> result = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < length; rowIndex++) {
            List<Boolean> row = new ArrayList<>(length);
            for (int cellIndex = 0; cellIndex < length; cellIndex++) {
                row.add(rand.nextInt(2) == 0);
            }
            result.add(row);
        }

        return result;
    }
}