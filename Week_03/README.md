#### 这周一直在加班，今天到家比较迟了，时间有限，在老师代码的基础上基本实现，并测试了一下，明天起来补上自己的demo

学习笔记

# 1.什么是高性能？

1. 高并发用户
2. 高吞吐量(我们默认的高性能是这个 QPS(每秒的请求数)/TPS(每秒的交易数))
3. 低延迟(偏向金融和核心的地方)
4. 系统延迟时间和系统响应时间：
   1. 响应时间(RRT)：在调用方的视角说的，这里包含了网络响应时间
   2. 延迟:说的是系统内部的，在系统侧的处理时间
5. 吞吐和延迟是什么关系？
   1. 一般延迟越低，吞吐越高；延迟高的系统，吞吐量也可能会高；
6. 高性能的副作用：
   1. 系统复杂度高
   2. 建设成本和维护成本高
   3. 故障或者bug导致的破坏性高
7. 应对策略：稳定性建设（混沌工程）
   1. 容量预估
   2. 爆炸半径
   3. 工程方面累计和改进

# 2.Netty如何实现高性能？

1. Netty的核心对象

   1. EventLoopGroup/EventLoop

      1. 包含了多个EventLoop，EventLoop可以看成是一个线程，负责进行详细的处理，EventLoopGroup可以看成是一个线程池；

   2. Bootstrap: 启动线程，开启socket

   3. SocketChannel: 连接

   4. ChannelInitializer: 初始化

   5. ChannelPipeline: 处理器链

   6. ChannelHandler: 处理器

      ChannelPipeline示意图：

   ![image-20210120211837418](C:\Users\shitiancheng\AppData\Roaming\Typora\typora-user-images\image-20210120211837418.png)

# 3.Netty 网络程序优化

1. 三次握手，四次挥手

   1. SYN:询问在不在线
   2. ACK:回复自己是否在线
   3. FIN:发起断开连接
   4. TimeWait 是会比较耗费性能的，在等待两个MSL才会变成CLOSE状态
   5. MSL:Linux是1分钟，Windows是2分钟，可以通过修改：ulimit -a/proc/sys/net/ipv4/tcp_fin_timeout, TcpTimedWaitDelay

2. Netty优化

   1、不要阻塞EventLoop

   2、系统参数优化ulimit -a/proc/sys/net/ipv4/tcp_fin_timeout, TcpTimedWaitDelay

   3、缓冲区优化SO_RCVBUF/SO_SNDBUF/SO_BACKLOG(正在建立的连接队列和处理完成的连接队列总和)/ REUSEXXX(复用)

   4、心跳频率周期优化: 心跳机制与断线重连

   5、内存与ByteBuffer优化DirectBuffer与HeapBuffer

   6、其他优化

   -ioRatio

   -Watermark

   -TrafficShaping

   **(1是最重要的)**



