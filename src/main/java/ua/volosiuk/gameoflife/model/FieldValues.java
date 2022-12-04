package ua.volosiuk.gameoflife.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class FieldValues {

    private List<List<Boolean>> values;

    public FieldValues(int length) {
        List<List<Boolean>> values = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < length; rowIndex++) {
            List<Boolean> row = new ArrayList<>(length);
            for (int cellIndex = 0; cellIndex < length; cellIndex++) {
                row.add(Boolean.FALSE);
            }
            values.add(row);
        }
        this.values = values;
    }
}
