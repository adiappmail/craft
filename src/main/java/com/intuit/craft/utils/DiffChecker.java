package com.intuit.craft.utils;

import com.intuit.craft.dto.BusinessProfileDto;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class DiffChecker {

    public List<String> getDifference(BusinessProfileDto object1, BusinessProfileDto object2) {
        List<String> attributeDiiferenceList = new ArrayList<>();
        if ((object1 == null && object2 != null) || (object1 != null && object2 == null)) {
            attributeDiiferenceList.add("Not Equal");
            return attributeDiiferenceList;
        } else if (object1 == null && object2 == null) {
            return null;
        } else if (!object1.equals(object2)) {
            attributeDiiferenceList.add("Not Equal");
            return attributeDiiferenceList;

        }
        return attributeDiiferenceList;
    }
}
