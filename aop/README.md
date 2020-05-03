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

## Video 5

### 5.1 WildCard.

execution(* get*())好像在我的这个版本的Spring中不管用，一直报错。
> 找到了原因是因为这里的get设置太广泛，导致一些内置的tomcat的一些类也被aop了，  
> 由于一些方法无法访问到所以一直报错无法启动

```
execution(* com.zyd.model.*.get*())
```

一些通配符的使用方法，
- 返回类型
- 限制package
- 方法名的通配符
- 方法参数的通配符

使用通配符的目的是让我们可以一次性匹配多个方法，因为通常我们希望切入的方法不止一个。

### 5.2 Pointcut.

通过创建Pointcut我们可以抽象出一些切入点，并且其他需要做AOP的方法就不需要自己定义何时执行，  
而是直接调用引用定义好的Pointcut就行。

> 很明显，如果我们给每个Aspect方法都创建一个单独的匹配（execution之类的），
> 那么一旦我们修改了业务逻辑中的类或者方法名，
> 我们想要再修改Aspect中的配置的话会非常的繁琐，
> 如果我们把这些匹配都抽象成Pointcut的话管理起来会非常的方便。


## Video 6

一些关于Pointcut的配置。

- execution：作用于方法
- within：作用于类

Pointcut的组合。  
```
allGetters() && allCircleMethods()  
```
通过组合用更少的Pointcut设计出更多的pattern。

> 随着我们的业务逻辑越来越复杂，我们可能会创建出非常复杂的Pointcut，
> 对于阅读代码的人来说很不友好，对于维护者来说也很难下手。
> 如果我们可以将复杂的Pointcut拆分成多个简单的Pointcut，并且通过排列组合来复用这些逻辑，
> 很显然是一个非常好的选择。

## Video 7

JoinPoints。  
一般用在方法上。可以获取到执行方法的一些信息。
- toString：执行了什么方法
- so on

@Before("args(some-args)")  
我们也可以在设置Pointcut的时候指定目标函数的执行参数，  
这样我们可以在Aspect里面获取到函数执行的参数值进行一些处理。

> 有时候我们不光是希望切入到某个方法中，在他的之前或者之后做一些事情，
> 我们同时也希望获取到目标方法的一些信息，这样能更灵活的执行我们的Aspect逻辑。
> 比如日志记录切入方法的执行参数值，返回类型等等。
> 那么JoinPoints就给我们提供了这样一个接口来实现这些功能。

## Video 8

- @After
- @AfterReturning
- @AfterThrowing

After稍微特殊一点，因为函数执行可能会抛出异常，
所以我们可以设置一些pointcut，他们的执行条件为要么函数正常返回，要么抛出异常。

@AfterReturning(pointcut='point-cut', returning='returnVal')  
通过这样的方式我们可以获取到切入方法的返回值来进行处理。
比如在某些场景中，我们希望日志记录某些特定方法的返回值，来进行异常排查等等。

同理，  
@AfterThrowing(pointcut='point-cut', throwing='ex')  
我们也可以在Aspect中获取到目标函数抛出的异常。

## Video 9

Around.

可以包裹目标函数，甚至决定是否执行目标函数。
一般可以用来做filter。

但是通常来说，我们要尽可能遵循最小原则，
如果你知道你的Aspect只需要在目标函数之后执行，那么用@Before就足够了。

## Video 10

Naming Conversion非常的重要。  
因为我们在描述匹配的时候我们是用的字符串匹配，
所以如果一个项目的组员不遵守一定的命名规则的话，
那么很容易会匹配不上某些类或者匹配到一些不想匹配的类。

使用Annotation来反向配置Aspect。
- 编写Aspect方法
- 编写自定义的annotation
- 配置Aspect方法，绑定方法到某个annotation上
- 在我们想要应用此Aspect方法的业务类方法上使用这个annotation

这样，业务类中的方法就和特定的Aspect方法绑定上了。（通过annotation）

> 为什么要这么做？
> 因为有时候我们想要给某些特定的方法绑定Aspect，
> 但是这些特定的方法很难找到规律，（意味着我们很难写出通用的Pointcut），
> 这时候通过annotation来绑定会显得更加灵活。  
> 但同时也增加了业务类的负担，因为他们要维护Aspect的配置。

## Video 11

使用xml进行AOP的配置。  
但是作者也强调说个人更喜欢annotation方式的配置，
但是毕竟有一些老项目可能还在使用xml的配置方式，
所以学习xml配置并无坏处。

## Video 12

AOP的底层原理用到了代理模式。
简单的来说目标类被代理类继承，
代理类重写了目标方法，并且在原始的方法的before和after的部分加入Aspect的逻辑。