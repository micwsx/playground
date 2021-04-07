package com.enjoy.redis;


import redis.clients.jedis.Jedis;

import java.io.File;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 10个人抢100块5个红包，每个红包随机分配金额，总金额100元。例：10+20+30+25+15=100
 * 模拟多个人多次抢红包场景。
 * @author wu
 *
 */
public class RedPacket {

    private Jediser jediser=new Jediser();


    public RedPacket(double amount,int number) {
        double balance=amount;
        Random random=new Random();
        double[] pieces= new double[number];
        double averageAmount= amount/number;
        for (int i = 0; i < pieces.length; i++) {
            if (i==pieces.length-1) {
                pieces[i]=balance;
            }else{
                double money=random.nextDouble()*averageAmount;

                pieces[i]=format(money);
                balance=format(balance-pieces[i]);
            }
            //System.out.println(pieces[i]+"-"+balance);
            jediser.sadd("redpacket", String.valueOf(pieces[i]));
        }
        // 6s失效
        jediser.expire("redpacket", 6);
        //System.out.println(jediser.scard("redpacket"));
    }

    private double format(double money){
        BigDecimal bigDecimal=new BigDecimal(money);
        double formatMoney=bigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        return formatMoney;
    }

    public void robPacket(){
        if (jediser.scard("redpacket")>0) {
            if (jediser.sismember("robber", Thread.currentThread().getName())) {
                System.out.println(Thread.currentThread().getName()+"已抢过红包，不能再抢！");
            }else{
                String member=jediser.srandmember("redpacket");
                if (member!=null) {
                    Long result=jediser.srem("redpacket", member);
                    if (result==1) {
                        jediser.sadd("robber", Thread.currentThread().getName());
                        jediser.expire("robber", 6);
                        System.out.println(Thread.currentThread().getName()+"抢到了"+member);
                    }else{
                        System.out.println("红包被抢失败！"+result);
                    }
                }else{
                    System.out.println("获取红包失败！"+member);
                }
            }
        }else{
            System.out.println(Thread.currentThread().getName()+"来晚了，红包已被抢完！");
        }
    }

    public void luaRobPacket(String user){
        File file = new File(Jedis.class.getClassLoader().getResource("").getPath());
        String rootDir = file.getParentFile().getParent();
        String luaFilePath=file.getAbsoluteFile()+"\\redPacket.lua";
        String script=jediser.readScript(luaFilePath);

//		Object result =jediser.eval(script, Arrays.asList("redpacket","robber"), Arrays.asList(user));
        Object result =jediser.eval(script,3,"redpacket","robber",user);
        if (result==null) {
            System.out.println(user+"已抢过");
        }else if(result.equals("0")){
            System.out.println("红包已抢完，"+user+"没有抢到！["+result+"]");
        }else{
            System.out.println(user+"抢到了"+result);
        }
    }




    static class Person extends Thread{

        private Thread next;
        /**
         * @return the next
         */
        public Thread getNext() {
            return next;
        }

        /**
         * @param next the next to set
         */
        public void setNext(Thread next) {
            this.next = next;
        }

        private RedPacket redPacket;
        public Person(String name,RedPacket redPacket) {
            super(name);
            this.redPacket=redPacket;
        }

        @Override
        public void run() {
            System.out.println(this.getName()+"开抢！");
            redPacket.robPacket();
            System.out.println(this.getName()+"结束！");
            try {
                if (next!=null) {
                    next.start();
                    this.next.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {


        //oneStepOne();

        //noThreadSafe();

        //threadSafe();

    }

    private static void threadSafe() throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(1);
        RedPacket redPacket=new RedPacket(100, 5);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    redPacket.luaRobPacket(Thread.currentThread().getName());
                }
            },"Thread["+0+"]").start();
        }

        System.out.println("rob after 3s");
        Thread.sleep(3000);
        latch.countDown();
    }

    private static void noThreadSafe() throws InterruptedException {
        CountDownLatch latch=new CountDownLatch(1);
        RedPacket redPacket=new RedPacket(100, 5);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    redPacket.robPacket();
                }
            },"Thread["+0+"]").start();
        }

        System.out.println("rob after 3s");
        Thread.sleep(3000);
        latch.countDown();
    }

    private static void oneStepOne() {
        RedPacket redPacket=new RedPacket(100, 5);

        Person firstPerson=new Person("Michael",redPacket);

        Person current=firstPerson;
        for (int i = 0; i < 3; i++) {
            Person p=new Person("Thread["+i+"]",redPacket);
            current.setNext(p);
            current=p;
        }
        firstPerson.start();
        System.out.println("waiting...");
        try {
            firstPerson.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Person thievePerson=new Person("Michael",redPacket);
        thievePerson.start();
    }
}
