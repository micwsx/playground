package com.enjoy.concurrent.singleton;

/**
 * @Author wu
 * @Date 4/7/2021 1:43 PM
 * @Version 1.0
 */
public class LanHan {
    private static LanHan instance;

    public static synchronized LanHan getInstance() {
        if (instance == null) {
            instance = new LanHan();
        }
        return instance;
    }
}


class LanHan3 {
    private static volatile LanHan3 instance;

    public static LanHan3 getInstance() {
        if (instance == null) {
            synchronized (LanHan3.class) {
                if (instance == null) {
                    instance = new LanHan3();
                }
            }
        }
        return instance;
    }
}

class LanHan2 {

    private static class InnerClass {
        private static LanHan2 instance = new LanHan2();
    }

    public static LanHan2 getInstance() {
        return InnerClass.instance;
    }
}

class EHan {
    private final static LanHan instance = new LanHan();

    public static LanHan getInstance() {
        return instance;
    }

}
