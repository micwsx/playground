package com.enjoy.hackerrank.search;

import java.math.BigDecimal;

/**
 * @author Michael
 * @create 12/31/2020 11:56 AM
 */
public class MakingCandies {

    public static void main(String[] args) {
//        long l = minimumPasses(1, 1, 6, 45);// 15次时， 25+4*5>45

//        long l = minimumPasses1(123456789012L, 215987654321L, 50000000000L, 1000000000000L);
        long l = minimumPasses(3, 13, 13, 1000000000000L);


//        long l = minimumPasses(38, 25, 22, 21);

//        long l = minimumPasses(3, 1, 2, 12);
//        long l = minimumPasses(1, 2, 1, 60);
//        long l = minimumPasses(3, 1, 2, 12);
        System.out.println(l);
    }

    /**
     * @param m the starting number of machines
     * @param w the starting number of workers
     * @param p the cost of a new hire or a new machine
     * @param n the number of candies to produce
     * @return
     */
    static long minimumPasses1(long m, long w, long p, long n) {
        long candies = 0;
        long passes = 0;
        long step = 0;
        long run = Long.MAX_VALUE;
        while (candies <= n) {
            step = (m > Long.MAX_VALUE / w) ? 0 : (p - candies) / (m * w);
            if (step <= 0) {
                // increment to equal m and w as possible as you can.
                long production = candies / p;
                if (m >= w + production) {
                    // increment w
                    w += production;
                } else if (w >= m + production) {
                    m += production;
                } else {
                    // divide into two parts.
                    long total = m + w + production;
                    m = total / 2;
                    w = total - m;
                }
                candies %= p;
                step = 1;
            }
            passes += step;
            if (step * m > Long.MAX_VALUE / w) {
                candies = Long.MAX_VALUE;
            } else {
                candies += step * m * w;
                run = Math.min(run, (long) (passes + Math.ceil((n - candies) / (m * w * 1.0))));
            }
        }
        return Math.min(passes, run);
    }

    static long minimumPasses(long m, long w, long p, long n) {
        long passes = 0;
        long candy = 0;
        long run = Long.MAX_VALUE;
        long step;

        while (candy < n) {
            step = (m > Long.MAX_VALUE / w) ? 0 : (p - candy) / (m * w);

            if (step <= 0) {
                long mw = candy / p;

                if (m >= w + mw) {
                    w += mw;
                } else if (w >= m + mw) {
                    m += mw;
                } else {
                    long total = m + w + mw;
                    m = total / 2;
                    w = total - m;
                }

                candy %= p;
                step = 1;
            }

            passes += step;

            if (step * m > Long.MAX_VALUE / w) {
                candy = Long.MAX_VALUE;
            } else {
                candy += step * m * w;
                run = Math.min(run, (long) (passes + Math.ceil((n - candy) / (m * w * 1.0))));
            }
        }

        return Math.min(passes, run);
    }


}
