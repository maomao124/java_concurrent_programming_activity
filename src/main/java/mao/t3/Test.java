package mao.t3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project name(项目名称)：java并发编程_活跃性
 * Package(包名): mao.t3
 * Class(类名): Test
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/9/5
 * Time(创建时间)： 12:00
 * Version(版本): 1.0
 * Description(描述)： 活锁
 */

public class Test
{
    private static final Logger log = LoggerFactory.getLogger(Test.class);

    /**
     * volatile保证可见性，保证获取的是最新的数据，但不能保证原子性
     */
    static volatile int count = 100;

    public static void main(String[] args)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (count > 0)
                {
                    try
                    {
                        Thread.sleep(30);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    count--;
                    log.debug("t1 count:" + count);
                }
            }
        }, "t1").start();

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (count < 200)
                {
                    try
                    {
                        Thread.sleep(30);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    count++;
                    log.debug("t2 count:" + count);
                }
            }
        }, "t2").start();
    }
}
