package com.study.leetcode.lc_9;

public class Foo {
    //控制变量
    private int flag = 0;
    //定义Object对象为锁
    private Object lock = new Object();
    public Foo() {
    }
    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock){
            //如果flag不为0则让first线程等待，while循环控制first线程如果不满住条件就一直在while代码块中，防止出现中途跳入，执行下面的代码，其余线程while循环同理
            while( flag != 0){
                lock.wait();
            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            //定义成员变量为 1
            flag = 1;
            //唤醒其余所有的线程
            lock.notifyAll();
        }
    }
    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock){
            //如果成员变量不为1则让二号等待
            while (flag != 1){
                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            //如果成员变量为 1 ，则代表first线程刚执行完，所以执行second，并且改变成员变量为 2
            flag = 2;
            //唤醒其余所有的线程
            lock.notifyAll();
        }
    }
    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock){
            //如果flag不等于2 则一直处于等待的状态
            while (flag != 2){
                lock.wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            //如果成员变量为 2 ，则代表second线程刚执行完，所以执行third，并且改变成员变量为 0
            printThird.run();
            flag = 0;
            lock.notifyAll();
        }
    }

    private static void FooTest01() throws InterruptedException{
        Foo foo = new Foo();
        Thread thread1 = new Thread(() -> {
            try {
                foo.first(() -> {
                    System.out.println("one");

                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                foo.second(() -> {
                    System.out.println("two");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                foo.third(() -> {
                    System.out.println("third");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }

    public static void main(String[] args) {
        System.out.println(SQL_LIST_0301.trim());
    }


    /**
     * 0201   和   0203  配合使用  查询欠费信息
     */
    public final static  String SQL_LIST_0301 = " SELECT temp.* from (SELECT temp0.* FROM (" +
            "  SELECT a.froomid \"roomId\",a.FCustomerID,cu.fphone \"phone\",cu.fname_l2 \"cusName\" /*客户名称*/,room.fnumber \"roomNumber\" /*物业房号*/,room.fname_l2 \"roomName\" /*房间名称*/,ROWNUM RN ," +
            "  rs.fpayedArea \"payedArea\" /*房间面积*/, " +
            "  nvl(a.arPreAmount,0) \"arPreAmount\" /*往年欠款*/,nvl(a.arCurrentAmount,0) \"arCurrentAmount\" /*本年未缴*/ from ( " +
            "  SELECT  ar.froomid,ar.FCustomerID, " +
            "  sum(nvl(ar.fartotalamount, 0) - nvl(ar.fpayedamount, 0)) arPreAmount ,null arCurrentAmount " +
            "  FROM T_PPM_PPMGeneralAR ar " +
            "  left join t_bd_period p on p.fid = ar.fperiodid " +
            "  WHERE ar.froomid in (select froom from yt_ins_GridManagement_Entry where fparentid in (select id from yt_ins_GridManagement where id = :gridManagementId ) ) " +
            "  and p.fperiodyear < :year group by  ar.froomid,ar.FCustomerID " +
            "  union all  " +

            "  SELECT  ar.froomid,ar.FCustomerID, " +
            "  null arPreAmount ,sum(nvl(ar.fartotalamount, 0) - nvl(ar.fpayedamount, 0)) arCurrentAmount " +
            "  FROM T_PPM_PPMGeneralAR ar " +
            "  left join t_bd_period p on p.fid = ar.fperiodid " +
            "  WHERE ar.froomid in (select froom from yt_ins_GridManagement_Entry where fparentid in (select id from yt_ins_GridManagement where id = :gridManagementId ) ) " +
            "  and p.fperiodyear = :year group by  ar.froomid,ar.FCustomerID " +
            "  ) a  " +
            "  left join t_she_room room on room.fid = a.froomid " +
            "  left join T_SHE_FDCCustomer cu on cu.fid = a.FCustomerID " +
            "  left join t_ppm_roomcustomersetting rs on rs.FRoomID=a.froomid and rs.fispayman=1 " +
            "  where (nvl(a.arPreAmount,0) + nvl(a.arCurrentAmount,0))<>0 " ;

}
