package info.uncovr;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Utility {
    
    public static List<Character> shuffle(List<Character> inputList) {
        int halfwayPoint = inputList.size() / 2;
        List<Integer> indexList = new LinkedList<>();
        for(int i = halfwayPoint; i < inputList.size(); i++) {
            indexList.add(i);
        }
        Collections.shuffle(indexList);
        
        for (int i = 0; i < halfwayPoint; i ++) {
            swap(inputList, i, indexList.get(i));
        }
        
        return inputList;
    }
    
    private static void swap(List<Character> inputList, int startIndex, int endIndex) {
        
        char endChar = inputList.get(endIndex);
        char startChar = inputList.get(startIndex);
        
        inputList.set(startIndex, endChar);
        inputList.set(endIndex, startChar);
    }
}