1. 首先演示一下OOM

   使用串行化GC，设置128m的堆大小进行运行

   参数如下:

   java -Xmx128m -Xms128m -XX:+UseSerialGC -XX:-UseAdaptiveSizePolicy -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.demo.log GCLogAnalysis

   YGC分析:

   ![image-20210117143550741](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117143550741.png)

   以该条为例：

   2021-01-17T14:18:19.170+0800: 0.098: [GC (Allocation Failure) 2021-01-17T14:18:19.170+0800: 0.098: [DefNew: 39033K->4351K(39296K), 0.0070604 secs] 44689K->22410K(126720K), 0.0071098 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 

   经过YGC回收之后，年轻代的堆大小从39033K减少到了4351K，整个堆得大小从44689k减少到了22410K，因此有44689-22410=22279K的对象被回收，有（39033-4351）-（44689-22410）=12403K的对象被晋升到老年代

   之后一直由于对象分配失败，进行FullGC，直到OOM

   ![image-20210117145331526](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117145331526.png)

   

1.串行化GC

运行命令:java -Xmx512m -Xms512m -XX:+UseSerialGC -XX:-UseAdaptiveSizePolicy -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.demo.Serial512log GCLogAnalysis

（后续就内存不一样，不再粘贴运行命令）

512M

生成13669个对象

![image-20210117153241364](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117153241364.png)

由图片我们可以得知，整个堆都被占满了

![image-20210117153420837](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117153420837.png)

![image-20210117153622063](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117153622063.png)

一共触发了25次GC，YGC10次，FullGC15次，共耗时610毫秒，每次GC的平均时间为24.4毫秒

1G

生成17826个对象

![image-20210117154818724](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117154818724.png)

![image-20210117154842537](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117154842537.png)

一共发生了17次GC，其中15次YGC，2次FGC，共消耗440毫秒，平均每次GC时间25.9毫秒

2G

共生成了16955个对象

![image-20210117155127967](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117155127967.png)

![image-20210117155154670](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117155154670.png)

一共发生了7次GC，其中7次YGC，0次FGC，共消耗410毫秒，平均每次GC时间58.6毫秒

4G

共生成对象15291个

![image-20210117160025734](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117160025734.png)

![image-20210117160040690](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117160040690.png)

一共发生了3次GC，其中3次YGC，0次FGC，共消耗270毫秒，平均每次GC时间90毫秒

为什么每次GC暂停的总时间短了，但是创建的对象反而少了？

2.并行GC

运行命令:java -Xmx512m -Xms512m -XX:+UseParallelGC -XX:-UseAdaptiveSizePolicy -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xloggc:gc.demo.Parallel512.log GCLogAnalysis

512M

创建了12407个对象

![image-20210117160857506](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117160857506.png)

![image-20210117160918130](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117160918130.png)

一共发生了30次GC，其中13次YGC，17次FullGC，共消耗640毫秒，平均每次GC时间21.3毫秒

1G

创建了20585个对象

![image-20210117161202406](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117161202406.png)

![image-20210117161217001](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117161217001.png)

一共发生了25次GC，其中21次YGC，4次FullGC，共消耗380毫秒，平均每次GC时间15.2毫秒

2G

生成23842个对象

![image-20210117161616828](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117161616828.png)

![image-20210117161626716](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210117161626716.png)

一共发生了13次GC，其中12次YGC，1次FullGC，共消耗290毫秒，平均每次GC时间22.3毫秒

4G

生成20692个对象

![image-20210119204629741](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119204629741.png)

![image-20210119204647073](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119204647073.png)

一共发生了5次GC，其中5次YGC，0次FullGC，共消耗140毫秒，平均每次GC时间28毫秒

3.CMS

512M

生成13155个对象

![image-20210119205721438](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119205721438.png)

![image-20210119205625493](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119205625493.png)

一共发生了25次GC，其中23次YGC，2次FullGC，共消耗790毫秒，平均每次GC时间12.7毫秒

1G

生成20274个对象

![image-20210119210056261](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119210056261.png)

![image-20210119210112398](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119210112398.png)

一共发生了19次GC，其中17次YGC，2次FullGC，共消耗850毫秒，平均每次GC时间16毫秒

2G

生成18018个对象

![image-20210119210238856](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119210238856.png)

![image-20210119210259275](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119210259275.png)

一共发生了7次GC，其中7次YGC，0次FullGC，共消耗420毫秒，平均每次GC时间35.6毫秒

4G

生成17922个对象

![image-20210119210620646](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119210620646.png)

![image-20210119210818144](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119210818144.png)

一共发生了7次GC，其中7次YGC，0次FullGC，共消耗420毫秒，平均每次GC时间60毫秒

4.G1

512M

生成11223个对象

![image-20210119211454913](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119211454913.png)

![image-20210119211515773](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119211515773.png)

一共发生了45次GC，其中4次YGC，1次FullGC，共消耗400毫秒，平均每次GC时间2.29毫秒

1G

生成16257个对象

![image-20210119212629886](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119212629886.png)

![image-20210119212639412](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119212639412.png)

一共发生了18次GC，其中18次YGC，0次FullGC，共消耗320毫秒，平均每次GC时间5.16毫秒

2G

生成19925个对象

![image-20210119212745796](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119212745796.png)

![image-20210119212754600](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119212754600.png)

一共发生了13次GC，其中13次YGC，0次FullGC，共消耗210毫秒，平均每次GC时间10.5毫秒

4G

生成20301个对象

![image-20210119212856009](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119212856009.png)

![image-20210119212921369](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210119212921369.png)



一共发生了15次GC，其中15次YGC，0次FullGC，共消耗230毫秒，平均每次GC时间15.3毫秒

顺便还测试了6G 共生成 21551个对象 8g 共生成21967个对象