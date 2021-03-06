# 3.JVM核心技术--调优分析与面试经验

### 1.GC 日志解读与分析*

1. 堆内存太小会oom
2. 堆太大，每次gc暂停的时间会比较长，吞吐量下降
3. 堆内存的使用，在不影响系统的使用情况下，尽量的小
4. 提升效率，节约成本

### 2.JVM 线程堆栈数据分析

### 3.内存分析与相关工具*

MetaspaceSize还是建议设置一下大小，设置一个不大不小的内存大小，因为我们可能会自己实现一些ClassLoader，但是如果有问题，一直在往里面加载的话，可以提早发现。

### 4.JVM 问题分析调优经验*

1.内存分配速率

1. 可以提升伊甸区大小来改善

2.过早提升

1. 频繁fullgc
2. 每次fullgc后老年代的使用率都很低10-20%
3. 提升速率接近分配速率

4. 可以提升年轻代来进行改善
5. 减少每次批处理的数量
6. 总体目标都是一致的，让临时数据能够在年轻代放得下

### 5.GC 疑难情况问题分析

1、查询业务日志，可以发现这类问题：请求压力大，波峰，遭遇降级，熔断等等， 基础服务、外部 API
依赖 。
2、查看系统资源和监控信息：
硬件信息、操作系统平台、系统架构；
排查 CPU 负载、内存不足，磁盘使用量、硬件故障、磁盘分区用满、IO 等待、IO 密集、丢数据、并发
竞争等情况；
排查网络：流量打满，响应超时，无响应，DNS 问题，网络抖动，防火墙问题，物理故障，网络参数调
整、超时、连接数。
3、查看性能指标，包括实时监控、历史数据。可以发现 假死，卡顿、响应变慢等现象；
排查数据库， 并发连接数、慢查询、索引、磁盘空间使用量、内存使用量、网络带宽、死锁、TPS、查
询数据量、redo日志、undo、 binlog 日志、代理、工具 BUG。可以考虑的优化包括： 集群、主备、只
读实例、分片、分区；
大数据，中间件，JVM 参数。
4、排查系统日志， 比如重启、崩溃、Kill 。
5、APM，比如发现有些链路请求变慢等等。
6、排查应用系统
排查配置文件: 启动参数配置、Spring 配置、JVM 监控参数、数据库参数、Log 参数、APM 配置、
内存问题，比如是否存在内存泄漏，内存溢出、批处理导致的内存放大、GC 问题等等；
GC 问题， 确定 GC 算法、确定 GC 的 KPI，GC 总耗时、GC 最大暂停时间、分析 GC 日志和监控指标：
内存分配速度，分代提升速度，内存使用率等数据。适当时修改内存配置；
排查线程, 理解线程状态、并发线程数，线程 Dump，锁资源、锁等待，死锁；
排查代码， 比如安全漏洞、低效代码、算法优化、存储优化、架构调整、重构、解决业务代码 BUG、第三
方库、XSS、CORS、正则；
单元测试： 覆盖率、边界值、Mock 测试、集成测试。
7、 排除资源竞争、坏邻居效应
8、疑难问题排查分析手段
DUMP 线程\内存；
抽样分析\调整代码、异步化、削峰填谷。

### 6.JVM 常见面试问题汇总*

STW的woker线程数 = n*5/8+3(n是处理器的数量)



第二周第二节课我请假了，笔记后面再补