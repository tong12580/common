package com.jokers.common.algorithm;

/**
 * MonteCarlo
 * <p>蒙特卡洛线性同余法</p>
 * Created by yuTong on 20180910.
 */
public class MonteCarlo {
    private static final int MAXN = 1 << 20;
    private int[] x;

    public MonteCarlo() {
        x = new int[MAXN];
    }

    public void rand() {
        x[0] = (int)(Math.random()*100 + 1);  // 随机种子(可以用日期产生)
        /* 保证m与a互质 */
        int a = 9;  // a = 4p + 1
        int b = 7;  // b = 2q + 1

        System.out.println(x[0]);
        /* 取前1w个数*/
        for(int i = 1; i < 10000; ++i) {
            x[i] = ( a * x[i-1] + b ) % MAXN;
            System.out.println(x[i]);
        }
    }

    public static void main(String[] args) {
        MonteCarlo mc = new MonteCarlo();
        mc.rand();
    }
}
