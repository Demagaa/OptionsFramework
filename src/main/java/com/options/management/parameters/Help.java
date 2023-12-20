package com.options.management.parameters;

import java.lang.String;
import java.util.HashMap;
import java.util.Map;

class Help {

    private static Map<java.lang.String, Option> optionMap;

    public Help(Map<java.lang.String, Option> optionMap) {
        Help.optionMap = optionMap;
    }

    public Help(java.lang.String arg) {
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


    private Map<Option, java.lang.String> swapValues() {
        HashMap<Option, java.lang.String> rev = new HashMap<>();
        for (Map.Entry<java.lang.String, Option> entry : optionMap.entrySet())
            rev.put(entry.getValue(), entry.getKey());
        return rev;
    }
}
