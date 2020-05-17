学习总结  
视频资源来自https://www.youtube.com/watch?v=y8IQb4ofjDo&list=PLqq-6Pq4lTTZSKAFG6aCDVDP86Qx4lNas



### 1 什么是microservice，为什么需要microservice？

**1 更好的组织系统**

通常商业系统都非常的庞大，各个组件之间的依赖关系错综复杂，如果我们对整个系统进行拆分，每个组件只完成某一部分特定的功能，整个系统的逻辑架构就会更加清晰。

**2 提高系统的可扩展性**

设想如果系统中的某一个功能，比如查询用户信息功能运行较慢，我们希望部署多个服务器以提高性能，由于此功能和系统中的其他部分是绑定在一起的，也就意味着其他的组件也会被默认扩展。  
这通常是一种资源的浪费，为了避免这种情况的出现，我们可以使用microservice。



### 2 如何在Spring中使用microservice？

实际上创建一个spring-web的应用，并且提供一些对外的接口，我们就可以将其称之为microservice。



### 3 microservice之间如何通信？

**1 使用RestFUL格式的接口**

现代web应用中，为了保持编程语言的独立性和可扩展性，每个microservice只需要暴露自己的API，就可以给其他的microservice提供相应的服务。

Spring提供了两种方式，

- 旧：RestTemplate
- 新：WebClient

Spring推荐使用新的WebClient，因为他的写法更加符合现代Java编程的写法，同时支持异步流，提高了性能。



### 4 microservice如何知道其他microservice的存在？

我们在开发应用的时候，肯定不希望硬编码，例如将其他microservice的url写入本地代码中。  
使用`spring-cloud`提供的discovery-server，我们可以轻松的实现microservice的互相访问。



### 5 discovery server做了什么？

首先我们有一台单独的discovery server（或者多台），server会记录所有注册到其server上的其他的service。

其次，任何独立的microservice都可以看做一个discovery client，他们会和discovery server通信，并做如下的两件事情：

1. 告诉discovery server自己是谁
2. 向discovery server请求特定service-name对应的访问地址（url）

这样，任何的一个microservice都不需要知道其他microservice的具体的url地址，而只需要对应的servicename即可互相访问。

另外，同一个service name可以注册多台microservice，这样我们就简单的实现了load balancer，当某一个microservice要求访问另一个microservice的时候，discovery server会从service列表中选择一个返回。