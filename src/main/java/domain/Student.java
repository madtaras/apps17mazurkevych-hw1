package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    public JsonObject toJsonObject() {
        JsonPair name = new JsonPair("name", new JsonString(this.name));
        JsonPair surname = new JsonPair("surname", new JsonString(this.surname));
        JsonPair year = new JsonPair("year", new JsonNumber(this.year));

        List<JsonObject> examsWithMarks = new ArrayList<>();

        for (Tuple<String, Integer> exam : exams) {
            examsWithMarks.add(new JsonObject(
                    new JsonPair("course", new JsonString(exam.key)),
                    new JsonPair("mark", new JsonNumber(exam.value)),
                    new JsonPair("passed", new JsonBoolean(exam.value >= 3))
            ));
        }

        JsonObject[] examsWithMarksArr = new JsonObject[exams.length];
        examsWithMarks.toArray(examsWithMarksArr);

        JsonPair examsJson = new JsonPair("exams", new JsonArray(examsWithMarksArr));

        return new JsonObject(name, surname, year, examsJson);
    }
}
