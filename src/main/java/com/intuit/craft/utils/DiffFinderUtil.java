package com.intuit.craft.utils;

import com.intuit.craft.dto.BusinessProfileDto;
import com.intuit.craft.exceptions.CraftException;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DiffFinderUtil<T> {

    private static final String STR_EMPTY = "";
    private static final String STR_UNDERSCORE = "_";
    private static final String STR_SMALL_BRACE_OPEN = "(";
    private static final String STR_SMALL_BRACE_CLOSE = ")";
    private static final String STR_DATE_PATTERN = "dd-MMM-YYYY HH:mm:ss";
    private static final String STR_OLD_VALUE = "oldValue";
    private static final String STR_NEW_VALUE = "newValue";




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


//    public void changeLog(Object old, Object newBP, Class<T> objClass ) {
//
//        if(!old.getClass().equals(objClass)) {
//            throw new CraftException("First parameter is not an instance of return type of method");
//        }
//        if(!newBP.getClass().equals(objClass)) {
//            throw new CraftException("Second parameter is not an instance of return type of method");
//        }
//
//        Map<String, Object> changeLog = new ch
//
//
//    }
//
//        private Map<String, Object> computeMapDiffs(Map<?, ?> oldMap, Map<?, ?> neoMap, String parent) {
//            Map<String, Object> changeLog = new HashMap<>();
//        oldMap.forEach((key1, oldVal) -> {
//            String key = (String) key1;
//            Object neoVal = neoMap.get(key);
//            if (null != oldVal && null != neoVal) {
//                if (oldVal instanceof Map && !((Map<?, ?>) oldVal).isEmpty()) {
//                    Map<String, Object>  newMap =  computeMapDiffs((Map<?, ?>) oldVal, (Map<?, ?>) neoVal, key + STR_UNDERSCORE);
//                    changeLog.put(key, newMap);
//                } else if (oldVal instanceof List && !((List<?>) oldVal).isEmpty()) {
//                    computeListDiffs((List<?>) oldVal, (List<?>) neoVal, parent + key, changeLog);
//                } else if (!oldVal.equals(neoVal)) {
//                    populateChangeLog(oldVal, neoVal, parent + key, changeLog);
//                }
//            } else if ((null != oldVal) || (null != neoVal)) {
//                populateChangeLog(oldVal, neoVal, parent + key, changeLog);
//            }
//        });
//
//
//    }
//
//    private void computeListDiffs(List<?> oldList, List<?> neoList, String key, Map<String, Object> changeLog) {
//
//        int oldSize = oldList.size();
//        int neoSize = neoList.size();
//        int itrSize = 0;
//
//        while (itrSize < oldSize && itrSize < neoSize) {
//            Object oldVal = oldList.get(itrSize);
//            Object neoVal = neoList.get(itrSize);
//            String parentKey = key + STR_SMALL_BRACE_OPEN + itrSize + STR_SMALL_BRACE_CLOSE + STR_UNDERSCORE;
//            if (oldVal instanceof Map && !((Map<?, ?>) oldVal).isEmpty()) {
//                computeMapDiffs((Map<?, ?>) oldList.get(itrSize),
//                        (Map<?, ?>) neoList.get(itrSize), parentKey);
//            } else {
//               changeLog.put(parentKey, populateChangeLog(oldVal, neoVal));
//            }
//
//            itrSize++;
//        }
//
//        if (oldSize != neoSize) {
//            if (itrSize >= oldSize) {
//                populateChangeLog(STR_EMPTY, neoList.subList(itrSize, neoSize), key);
//            } else {
//                populateChangeLog(oldList.subList(itrSize, oldSize), STR_EMPTY, key);
//            }
//        }
//
//    }
//
//    private Map<String, Object> populateChangeLog(T oldVal, T neoVal) {
//        Map<String, Object> tempMap = new HashMap<>();
//        tempMap.put(STR_OLD_VALUE, oldVal);
//        tempMap.put(STR_NEW_VALUE, neoVal);
//        return tempMap;
//    }
}
