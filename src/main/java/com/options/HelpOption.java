package com.options;

import java.util.HashMap;
import java.util.Map;

final class HelpOption {

    private static Map<String, Option> optionMap;

    public HelpOption(Map<String, Option> optionMap) {
        HelpOption.optionMap = optionMap;
    }

    public HelpOption(String arg) {
        System.out.println(getResult());
    }

    private String getResult() {
        StringBuilder result = new StringBuilder();
        result.append("List of defined options: \n");
        result.append("List of alias        |" +
                " Type of parameter  |" +
                " Default value                                      |" +
                " Description\n");


        Map<Option, String> swapped = swapValues();

        fillResult(swapped, result);

        return result.toString();
    }

    private void fillResult(Map<Option, String> swapped, StringBuilder result) {
        for (Map.Entry<Option, String> entry : swapped.entrySet()) {
            Option option = entry.getKey();
            StringBuilder aliasAsString = new StringBuilder();
            for (String alias : option.getAlias()) {
                aliasAsString.append(alias).append(" ");
            }

            String formattedDesc = option.getDesc();
            result.append(String.format("%-20s | %-18s | %-50s | %s\n",
                    aliasAsString.toString().trim(), option.getType().getSimpleName(),
                    option.getDef(), formattedDesc));
        }
    }


    private Map<Option, String> swapValues() {
        HashMap<Option, String> rev = new HashMap<>();
        for (Map.Entry<String, Option> entry : optionMap.entrySet())
            rev.put(entry.getValue(), entry.getKey());
        return rev;
    }
}
