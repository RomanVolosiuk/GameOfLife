package ua.volosiuk.gameoflife.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.volosiuk.gameoflife.exception.CustomConversionException;

import java.util.List;

@Component
public class CustomConverter implements Converter<String, List<List<Boolean>>> {

    private static final TypeReference<List<List<Boolean>>> PAYLOAD_TYPE = new TypeReference<>() {
    };
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<List<Boolean>> convert(String source) {
        try {
            return mapper.readValue(source, PAYLOAD_TYPE);
        } catch (JsonProcessingException e) {
            throw new CustomConversionException("Error converting |" + source + "| to target type List<List<Boolean>>");
        }
    }
}
