microservice中需要关注的另一个重要问题。处理容错。



## 1 microservice可能遇到的问题

### 1.1 服务宕机

如果我们需要互相通信的某一个microservice挂了，那么我们怎么处理。

简单的方法：提供多个instance。

### 1.2 服务响应速度慢

通常某一些microservice需要依赖其他microservice提供的数据，如果microservice的响应速度很慢，会拖垮整个系统的响应速度，我们如何处理。

Spring是在tomcat的基础上实现的，tomcat的工作原理来说，对于每一个单独的request，tomcat都会创建一个thread来处理他。  
那么问题来了，如果我们需要访问某个microservice但是该服务的响应非常慢的话，他会一直占用一个线程直到响应到达或者timeout。  
通常来说服务器的资源也是有限的，当用户的访问速度超过了线程的消耗速度之后，整个系统都会被拖垮。

解决方法：timeout。

### 1.3 timeout不能解决所有的问题

因为timeout处理thread的速度可能慢于request所创建的thread的速度。



## 2 怎么解决？

几个步骤

1. 侦测到错误的发生（速度慢，宕机）
2. 暂时避免向发生错误的microservice发送请求
3. 在microservice恢复后重连

断路器模式（Circuit breaker pattern）



## 3 断路器模式的具体实现

#### 什么情况下断电

- 考虑最后执行的request的数量
- 考虑的request中有多少个失败了
- timeout的阈值

#### 什么情况下断电恢复

- 断电到恢复的时长

#### 断电的时候我该怎么回复request

- 抛出异常
- 使用一个默认的回复方式
- 使用前一次的数据（cache）

### 3.1 Hystrix



