package Utils;

import java.util.ArrayList;

public class History {

    private static final ArrayList<String>
            history =
            new ArrayList<>();

    public static void add(
            String keyword) {

        history.add(keyword);
    }

    public static void show() {

        System.out.println(
                "\n===== HISTORY =====");

        for (String s : history) {

            System.out.println(s);
        }
    }
}