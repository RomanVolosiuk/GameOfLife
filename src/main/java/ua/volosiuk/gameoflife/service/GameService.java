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

    public List<List<Boolean>> initListList(int sideLength) {
        logger.log(Level.INFO, "service / initListList() started" );
        List<List<Boolean>> values = empty(sideLength);
        for (List<Boolean> row : values) {
            row.replaceAll(ignored -> rand.nextInt(2) == 0);
        }
        return values;
    }

    public List<List<Boolean>> nextStep(List<List<Boolean>> values) {
        logger.log(Level.INFO, "service / nextStep() started");
        List<List<Boolean>> calculatedField = empty(values.size());

        int size = values.size();
        for (int i = 0; i < size; i++) {
            List<Boolean> r = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {

                int neighbours = 0;
                for (int x = -1; x <= 1; x += 1) {
                    for (int y = -1; y <= 1; y += 1) {
                        if (!(y == 0 && x == 0)) {
                            int row = i + y;
                            int col = j + x;

                            if (row < 0)
                                row = size - 1;
                            else if (row == size)
                                row = 0;

                            if (col < 0)
                                col = size - 1;
                            else if (col == size)
                                col = 0;

                            if (values.get(row).get(col)) {
                                neighbours++;
                            }
                        }
                    }
                }
                boolean nextElement;
                boolean element = values.get(i).get(j);

                if (element && neighbours > 1 && neighbours < 4) {
                    nextElement = true; // still alive
                } else if (!element && neighbours == 3) {
                    nextElement = true; // to live
                } else {
                    nextElement = false; // to die
                }
                r.add(j, nextElement);
            }
            calculatedField.set(i, r);
        }
        return calculatedField;
    }

    private List<List<Boolean>> empty(int length) {
        logger.log(Level.INFO, "service / empty() started");
        List<List<Boolean>> result = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < length; rowIndex++) {
            List<Boolean> row = new ArrayList<>(length);
            for (int cellIndex = 0; cellIndex < length; cellIndex++) {
                row.add(Boolean.FALSE);
            }
            result.add(row);
        }
        return result;
    }
}