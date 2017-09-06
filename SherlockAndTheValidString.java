package hackerrank;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class SherlockAndTheValidString {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        if(validation(s))
            System.out.println("YES");
        else System.out.println("NO");

    }

    private static boolean validation(String s) {
        Map<Character, Integer> letterMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if(letterMap.containsKey(s.charAt(i))) {
                int freq = letterMap.get(s.charAt(i));
                letterMap.put(s.charAt(i), freq+1);
            }
            else
                letterMap.put(s.charAt(i), 1);
        }

        //System.out.println(letterMap);

        Map<Integer, Integer> letterFreq = new TreeMap<>();
        //key - frequency, value - how many letters have this frequency
        for(Map.Entry<Character, Integer> pair : letterMap.entrySet()) {
            int freq = pair.getValue();
            if(letterFreq.containsKey(freq)) {
                int letterAmount = letterFreq.get(freq);
                letterFreq.put(freq, letterAmount+1);
            }
            else
                letterFreq.put(freq, 1);
        }

        //System.out.println(letterFreq);

        if(letterFreq.size() == 1) // all has same frequency
            return true;
        if(letterFreq.size() > 2) // more than one letter has different frequency
            return false;

        int minFreq = 0, minFreqAmount = 0;
        int maxFreq = 0, maxFreqAmount = 0;

        for(Map.Entry<Integer, Integer> pair : letterFreq.entrySet()) {
            int freq = pair.getKey();
            int amount = pair.getValue();

            if(minFreq==0) { //it is first elem. We are sure in it, because we use TreeMap
                minFreq = freq;
                minFreqAmount = amount;
            }

            else { // second element
                maxFreq = freq;
                maxFreqAmount = amount;
            }
        }

        //System.out.println("min = " + minFreq + " " + minFreqAmount + " max = " + maxFreq + " " + maxFreqAmount);

        if (minFreq == 1 && minFreqAmount == 1) // like in aabbccd
            return true;
        if (maxFreqAmount == 1 && maxFreq == minFreq+1 ) //only one letter with bigger frequency and it is not too big
            // like abcdd, not abcddee or abcddd
            return true;
        return false;

    }
}
