package com.xiaojiefang;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by wangaichao on 17/2/18.
 */
public class E1_1 {
    public static void E1_1_1()
    {
        StdOut.println(Thread.currentThread().getStackTrace()[1].getMethodName());


    }

    public static void main(String[] args)
    {
        E1_1_1();
    }
}
