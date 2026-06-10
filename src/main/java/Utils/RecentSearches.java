package Utils;

import java.util.ArrayList;

public class RecentSearches {

    private static ArrayList<String> searches =
            new ArrayList<>();

    public static void addSearch(
            String keyword) {

        if (keyword == null
                || keyword.trim().isEmpty()) {

            return;
        }

        searches.add(keyword);

        if (searches.size() > 10) {

            searches.remove(0);
        }
    }

    public static ArrayList<String>
    getSearches() {

        return searches;
    }

    public static String getSearchHistory() {

        if (searches.isEmpty()) {

            return "No recent searches.";
        }

        StringBuilder builder =
                new StringBuilder();

        for (int i = searches.size() - 1;
             i >= 0;
             i--) {

            builder.append(i + 1)
                    .append(". ")
                    .append(searches.get(i))
                    .append("\n");
        }

        return builder.toString();
    }
}