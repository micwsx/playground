package com.enjoy.concurrent.optimization;

/**
 * 减小锁的粒度（锁分离）
 * 缩小锁的范围(不用加锁代码区域，不要加锁)
 * 加锁代码块（执行时间不要太长）
 * 读写锁替换独占锁（可以提高读速度）
 * 避免多余的锁（乱加锁会影响性能）
 */
public class LockOptimize {

    private Object objectA = new Object();
    private Object objectB = new Object();

    public synchronized void setObjectA(Object a) {
        this.objectA = a;
    }

    public synchronized void setObjectB(Object b) {
        this.objectB = b;
    }

    //--------------锁分离-----------------------
    public void setObjectAOptimization(Object a) {
        synchronized (objectA) {
            this.objectA = a;
        }
    }

    public void setObjectBOptimization(Object b) {
        synchronized (objectB) {
            this.objectB = b;
        }
    }


}
