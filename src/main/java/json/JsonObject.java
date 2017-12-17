package json;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private ArrayList<JsonPair> collection;

    public JsonObject(JsonPair... jsonPairs) {
        collection = new ArrayList<>();
        Collections.addAll(collection, jsonPairs);
    }

    @Override
    public String toJson() {
        StringBuilder result = new StringBuilder("");
        // Add opening bracket
        result.append("{");

        for (int i = 0, k = collection.size(); i < k; i++) {
            result.append(collection.get(i).key);
            result.append(": ");
            result.append(collection.get(i).value.toJson());

            if (i != k - 1) result.append(", ");
        }

        // Add closing bracket
        result.append("}");

        return result.toString();
    }

    public void add(JsonPair jsonPair) {
        // ToDo
    }

    public Json find(String name) {
        // ToDo
        return null;
    }

    public JsonObject projection(String... names) {
        // ToDo
        return null;
    }
}
