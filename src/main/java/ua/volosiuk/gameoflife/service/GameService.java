package ua.volosiuk.gameoflife.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.volosiuk.gameoflife.model.FieldValues;
import ua.volosiuk.gameoflife.model.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private final Random rand = new Random();
    Structure structure = new Structure();

    // Генеруємо рандомне поле, перезаписуемо в змінну та викликаемо calculateNextStructure()
    public void toCreateRandomStructure () {
        int size = structure.getEdgeLength();
        List<List<Integer>> randomField = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(j, rand.nextInt(2));
            }
            randomField.add(i, row);
        }
        structure.setNextStructure(randomField);
        calculateNextStructure(randomField, size);
    }

    public void calculateNextStructure (List<List<Integer>> previousField, int size) {
        List<List<Integer>> calculatedStructure = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            List<Integer> r = new ArrayList<>();
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

                            if (previousField.get(row).get(col) == 1) {
                                neighbours++;
                            }
                        }
                    }
                }

                int nextElement;
                int element;
                element = previousField.get(i).get(j);

                if (element == 1 && neighbours > 1 && neighbours < 4) {
                    nextElement = 1; // still alive
                } else if (element == 0 && neighbours == 3) {
                    nextElement = 1; // to live
                } else {
                    nextElement = 0; // to die
                }
                r.add(j, nextElement);
            }
            calculatedStructure.add(i, r);
        }
        structure.setNextStructure(calculatedStructure);
        outToConsole(calculatedStructure);
    }

    public void doNextStep () {
        calculateNextStructure(structure.getNextStructure(), structure.getEdgeLength());
    }

    public void outToConsole (List<List<Integer>> field) {

        for (int i = 0; i < structure.getEdgeLength() * 3; i++) {
            System.out.print("-");
        }
        field.forEach(System.out::println);
    }

    public FieldValues initField(int length) {
        FieldValues fieldValues = new FieldValues(length);
        List<List<Boolean>> values = fieldValues.getValues();
        for (List<Boolean> row : values) {
            row.replaceAll(ignored -> rand.nextInt(2) == 0);
        }
        return fieldValues;
    }

    // todo: calculate next step
    public FieldValues nextStep(FieldValues fieldValues) {
        return fieldValues;

    }
}

