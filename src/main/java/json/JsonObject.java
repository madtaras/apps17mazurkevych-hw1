package json;

import java.util.ArrayList;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private ArrayList<JsonPair> collection;

    public JsonObject(JsonPair... jsonPairs) {
        collection = new ArrayList<>();
        for (JsonPair pair: jsonPairs) {
            this.add(pair);
        }
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
        Boolean shouldPairBeAdded = true;

        for (int i = 0, k = collection.size(); i < k; i++) {
            if (jsonPair.key.equals(collection.get(i).key)) {
                collection.set(i, jsonPair);
                shouldPairBeAdded = false;
            }
        }

        if (shouldPairBeAdded) collection.add(jsonPair);
    }

    public boolean contains(String name) {
        for (JsonPair pair : collection) {
            if (pair.key.equals(name)) return true;

        }
        return false;
    }

    public Json find(String name) {
        for (JsonPair pair : collection) {
            if (pair.key.equals(name)) return pair.value;

        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject result = new JsonObject();

        for (String name: names) {
            if (this.contains(name)) {
                result.add(new JsonPair(name, this.find(name)));
            }
        }

        return result;
    }
}
