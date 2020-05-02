# 笔记

学习资料来自[Spring Tutorial 24 - Introduction to AOP](https://www.youtube.com/watch?v=QdyLsX0nG30&list=PLE37064DE302862F8)

## Video 1

Functional Programming.

Difference between FP and Object Oriented Programming.

**问题**  
但是这里有个问题，并不是所有的处理都可以规约到某个class里的。

比如每个class都会用到的方法，例如Logger。

一个比较naive的想法是我们可以将这些方法抽象成一个新的class，
然后其他的class引用这个抽象出来的公共class。

抽象成一个Logger类也存在一些问题。

- 和业务类关联过多
- 业务类仍然需要调用Logger类的方法
- 无法一次性修改所有的方法

AOP解决了这个问题。

通过创建一些Aspect，例如LoggingAspect，
来解决这个问题。
这样我们就不用修改我们的业务代码，业务代码是非常干净的。

然后我们通过`Aspect Configuration`来让Aspect和业务代码交互工作。

Aspect允许我们在目标方法的前后wrapping一些特定的代码。

实现步骤：

- 编写Aspect
- 配置Aspect

## Video 4

如何实现一个最简单的AOP配置。
由于这个视频比较老，2011年的视频，所以基本上是基于xml进行的配置。  
我查阅了相关的资料，用annotation配置了一遍，
所以这个project是没有用到任何xml的。

### 4.1 创建一个class LoggingAspect

用@Aspect 和 @Component标记这个class，
前者声明这个class是一个aspect类，后者声明这个class为Spring中的一个bean。

### 4.2 在LoggingAspect中创建一个method

用@Before标记这个method，
表示这个方法将在某个特定的条件下被执行。

这里视频中使用@Before("execution(public String getName())")，
表示在遇到满足getNawme条件的方法被执行时，这个被标记的方法会在这之前被执行。

### 4.3 创建class AppConfig

并标记为@Configuration和@EnableAspectJAutoProxy。  
让Aspect的配置有效化。

That's it!

这样我们就实现了一个最基本的AOP。

启动应用，然后在网址中输入localhost:8080/circle/xxx。
如果可以看到console口输出了Aspect中的内容（Advice run. Get Method called），那么你就成功了。

