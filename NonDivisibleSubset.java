package hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NonDivisibleSubset {
    private static int div;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sizeS = sc.nextInt();
        div = sc.nextInt();
        int[] arrayS = new int[sizeS];
        for(int i = 0; i < sizeS; i++){
            arrayS[i] = sc.nextInt();
        }

        //System.out.println(Arrays.toString(check(arrayS, k)));
        //collect(arrayS, k);
        int res = count(arrayS);
        System.out.println(res);
    }

    private static int[] check(int[]array){
        int[] arrayDiv = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayDiv[i]=array[i]%div;
        }
        return arrayDiv;
    }

    private static int[] collect(int[]array){
        Arrays.sort(array);
        //System.out.println(Arrays.toString(array));
        int[]amount = new int[div];
        for (int i = 0; i < div; i++) {
            amount[i]=0;
        }
        for (int i = 0, remain = 0; i < array.length && remain < div; ) {
            if (array[i]==remain){
                amount[remain]++;
                i++;
            }
            else{
                remain++;
            }
        }
        //System.out.println(Arrays.toString(amount));
        return amount;
    }

    private static Map<Integer, Integer> collect_(int[]array) {
        Map<Integer, Integer> remainMap = new HashMap<>();
        for(int i = 0; i < array.length; i++) {
            int counter=0;
            for(int j=i+1;j< array.length;j++) {
                if(array[i]==array[j])
                    counter++;
            }
            remainMap.put(i,counter);
        }
        return remainMap;
    }

    private static int count_(int[]array){

        int[]arrayAmount = collect(check(array));

        int firstAmount = 0;
        int endAmount = 0;

        //odd
        if (div%2==1) {
            for (int i = 1; i <= div / 2; i++) {
                firstAmount += arrayAmount[i];
            }
            for (int i = div/2+1; i <div ; i++) {
                endAmount += arrayAmount[i];
            }
        }
        //even
        if(div%2==0) {
            for (int i = 1; i <= div / 2 - 1; i++) {
                firstAmount += arrayAmount[i];
            }
            firstAmount++;

            for (int i = div/2 + 1; i <div ; i++) {
                endAmount += arrayAmount[i];
            }
            endAmount ++;
        }

        //System.out.println(firstAmount + " " + endAmount);

        return Math.max(firstAmount, endAmount);

    }

    private static int count(int[]array) {
        int[]arrayAmount = collect(check(array));

        int amount = 0;

        if (div==0 || div == 1 || div == 2)
            return 1;

        //System.out.println(Arrays.toString(arrayAmount));

        if(div%2==1) {
            for (int i = 1; i <= div / 2; i++) {
                amount += Math.max(arrayAmount[i], arrayAmount[div - i]);
            }
        }
        if(div%2==0) {
            for (int i = 1; i <= div/2 - 1; i++) {
                amount += Math.max(arrayAmount[i], arrayAmount[div-i]);
            }
            amount++;
        }

        return amount;
    }

    private static int count__(int[]array) {

        if (div==0 || div == 1)
            return 1;
        if(div == 2)
            return 2;

        Map<Integer, Integer> remainMap = collect_(check(array));
        int amount = 0;

        for (int i = 1; i <= div/2; i++) {
            if(remainMap.containsKey(i) && remainMap.containsKey(div-i)) {
                amount += Math.max(remainMap.get(i), remainMap.get(div-i));
            }
            else if (remainMap.containsKey(i)) {
                amount += remainMap.get(i);
            }
            else if (remainMap.containsKey(div-i))
                amount += remainMap.get(div-i);
        }
        return amount;
    }
}
