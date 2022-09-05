package mao.t1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project name(项目名称)：java并发编程_活跃性
 * Package(包名): mao.t1
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/5
 * Time(创建时间)： 11:22
 * Version(版本): 1.0
 * Description(描述)： 死锁
 */

public class Test
{
    /**
     * lock1
     */
    private static final Object lock1 = new Object();

    /**
     * lock2
     */
    private static final Object lock2 = new Object();

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (lock1)
                {
                    log.debug("t1获得锁1");
                    log.debug("t1尝试获取锁2");
                    synchronized (lock2)
                    {
                        log.debug("t1获得锁2");
                        log.debug("todo...");
                    }
                }
            }
        }, "t1").start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                synchronized (lock2)
                {
                    log.debug("t2获得锁2");
                    log.debug("t2尝试获取锁1");
                    synchronized (lock1)
                    {
                        log.debug("t2获得锁1");
                        log.debug("todo...");
                    }
                }
            }
        }, "t2").start();


        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                log.info("程序退出");
            }
        },"ShutdownHook"));
    }

}
