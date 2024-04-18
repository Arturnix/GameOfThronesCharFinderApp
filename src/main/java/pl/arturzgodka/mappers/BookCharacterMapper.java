package pl.arturzgodka.mappers;

import pl.arturzgodka.datamodel.BookCharacter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;

public final class BookCharacterMapper {
    public static BookCharacter mapJSONToCharacter(String characterJSON) {
        JSONObject object;

        JSONTokener tokener = new JSONTokener(characterJSON);
        try {
            object = new JSONObject(tokener);
        } catch (JSONException e) {
            tokener = new JSONTokener(characterJSON);
            object = new JSONArray(tokener).getJSONObject(0);
        }
        String name = object.getString("name");
        String gender = object.getString("gender");
        String culture = object.getString("culture");
        List<String> titles = jsonArrayToListOfStringsConverter(object.getJSONArray("titles"));
        List<String> aliases = jsonArrayToListOfStringsConverter(object.getJSONArray("aliases"));
        String father = object.getString("father");
        String mother = object.getString("mother");
        String spouse = object.getString("spouse");
        List<String> allegiances = jsonArrayToListOfStringsConverter(object.getJSONArray("allegiances"));
        List<String> tvSeries = jsonArrayToListOfStringsConverter(object.getJSONArray("tvSeries"));
        List<String> playedBy = jsonArrayToListOfStringsConverter(object.getJSONArray("playedBy"));

        return new BookCharacter(name, gender, culture, titles, aliases, allegiances, playedBy, tvSeries, mother, father, spouse);
    }

    private static List<String> jsonArrayToListOfStringsConverter(JSONArray jsonArray) {
        List<String> strings = new ArrayList<>();
        jsonArray.forEach(obj -> strings.add(obj.toString()));

        return strings;
    }
}
