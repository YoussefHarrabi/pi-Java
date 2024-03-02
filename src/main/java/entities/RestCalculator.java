package entities;

import java.util.HashMap;
import java.util.Map;

public class RestCalculator {
    private static final Map<String, Map<String, Integer>> restDaysMap = new HashMap<>();

    // Static initialization block to populate the rest days map
    static {
        // Fracture rest days
        Map<String, Integer> fractureRestDays = new HashMap<>();
        fractureRestDays.put("Low", 7);     // 1 week
        fractureRestDays.put("Medium", 14); // 2 weeks
        fractureRestDays.put("High", 21);   // 3 weeks
        restDaysMap.put("Fracture", fractureRestDays);

        // Sprain rest days
        Map<String, Integer> sprainRestDays = new HashMap<>();
        sprainRestDays.put("Low", 3);       // 3 days
        sprainRestDays.put("Medium", 7);    // 1 week
        sprainRestDays.put("High", 14);     // 2 weeks
        restDaysMap.put("Sprain", sprainRestDays);

        // Burn rest days
        Map<String, Integer> burnRestDays = new HashMap<>();
        burnRestDays.put("Low", 5);         // 5 days
        burnRestDays.put("Medium", 10);     // 10 days
        burnRestDays.put("High", 14);       // 2 weeks
        restDaysMap.put("Burn", burnRestDays);

        // Cut rest days
        Map<String, Integer> cutRestDays = new HashMap<>();
        cutRestDays.put("Low", 3);          // 3 days
        cutRestDays.put("Medium", 5);       // 5 days
        cutRestDays.put("High", 7);         // 1 week
        restDaysMap.put("Cut", cutRestDays);

        // Bruise rest days
        Map<String, Integer> bruiseRestDays = new HashMap<>();
        bruiseRestDays.put("Low", 2);       // 2 days
        bruiseRestDays.put("Medium", 3);    // 3 days
        bruiseRestDays.put("High", 5);      // 5 days
        restDaysMap.put("Bruise", bruiseRestDays);

        // Concussion rest days
        Map<String, Integer> concussionRestDays = new HashMap<>();
        concussionRestDays.put("Low", 7);   // 1 week
        concussionRestDays.put("Medium", 14); // 2 weeks
        concussionRestDays.put("High", 21); // 3 weeks
        restDaysMap.put("Concussion", concussionRestDays);

        // Whiplash rest days
        Map<String, Integer> whiplashRestDays = new HashMap<>();
        whiplashRestDays.put("Low", 10);    // 10 days
        whiplashRestDays.put("Medium", 14); // 2 weeks
        whiplashRestDays.put("High", 21);   // 3 weeks
        restDaysMap.put("Whiplash", whiplashRestDays);

        // Laceration rest days
        Map<String, Integer> lacerationRestDays = new HashMap<>();
        lacerationRestDays.put("Low", 3);   // 3 days
        lacerationRestDays.put("Medium", 5); // 5 days
        lacerationRestDays.put("High", 7);   // 1 week
        restDaysMap.put("Laceration", lacerationRestDays);

        // Abrasions rest days
        Map<String, Integer> abrasionsRestDays = new HashMap<>();
        abrasionsRestDays.put("Low", 2);    // 2 days
        abrasionsRestDays.put("Medium", 3); // 3 days
        abrasionsRestDays.put("High", 5);   // 5 days
        restDaysMap.put("Abrasions", abrasionsRestDays);

        // Internal bleeding rest days
        Map<String, Integer> internalBleedingRestDays = new HashMap<>();
        internalBleedingRestDays.put("Low", 10);     // 10 days
        internalBleedingRestDays.put("Medium", 14);  // 2 weeks
        internalBleedingRestDays.put("High", 21);    // 3 weeks
        restDaysMap.put("Internal bleeding", internalBleedingRestDays);
    }

    public static int calculateRestDays(String injuryType, String severity) {
        // Retrieve rest days from the map based on injury type and severity
        Map<String, Integer> severityRestDays = restDaysMap.getOrDefault(injuryType, new HashMap<>());
        return severityRestDays.getOrDefault(severity, 0); // Return rest days or 0 if not found
    }
}
