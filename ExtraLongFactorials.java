package hackerrank;

import java.math.BigInteger;
import java.util.Scanner;

public class ExtraLongFactorials {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigInteger number = sc.nextBigInteger();
        BigInteger res = factorial(number);
        System.out.println(res);

    }

    private static BigInteger factorial(BigInteger x){
        if (x.floatValue() == 0 || x.floatValue() == 1)
            return BigInteger.ONE;
        return karatsuba(x, factorial(x.subtract(BigInteger.ONE))); // x * (x-1)!
    }

    private static BigInteger karatsuba (BigInteger x, BigInteger y){
        if (x.floatValue() < 4 || y.floatValue() < 4)
            return x.multiply(y);

        int N = Math.max(x.bitLength(),y.bitLength());
        N/=2;

        // x = a + 2^N b,   y = c + 2^N d
        BigInteger b = x.shiftRight(N); // b = x>>N <=> b = x/2^N
        BigInteger a = x.subtract(b.shiftLeft(N)); //a=x-b*2^N = x - x/2^N*2^N
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

        //x*y = (a+2^N*b)* (c+2^N*d) = ac +2^N*ad + 2^N*bc + 2^N*2^N*bd = ac + 2^N(ad + bc) + 2^2N*bd
        BigInteger ac    = karatsuba(a, c);
        BigInteger bd    = karatsuba(b, d);
        //BigInteger abcd = karatsuba(a,d).add(karatsuba(b,c));
        BigInteger abcd  = karatsuba(a.add(b), c.add(d)); //(a+b)(c+d) = ac + ad + bc + bd

        BigInteger res1 = ac.add(bd.shiftLeft(2 * N)); //a*c + bd<<2*N = ac + bd*2^2N
        BigInteger res2 = abcd.subtract(ac).subtract(bd).shiftLeft(N);
        BigInteger res = res1.add(res2);  //ac + bd*2^2N + (ac + ad + bc + bd - ac - bd)*2^N = ... + (ad+bc)*2^N
        return res;
    }

    private int numLength(long number){
        String num = Long.toString(number);
        return num.length();
    }

}
