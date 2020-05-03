# 前言

来自[Spring Boot + Spring Security with JPA authentication and MySQL from scratch - Java Brains](https://www.youtube.com/watch?v=TNt3GHuayXs)
的教学视频。

如何使用

- spring-boot
- spring-security
- jpa
- mysql

来实现一个简单的认证功能。

## 1 创建SecurityConfiguration

SecurityConfiguration用来配置我们的验证逻辑。

比如，对于特定的url，我们希望什么样的用户（角色）可以访问，什么样的用户（角色）会被拒绝。

**重点**  
首先SpringSecurity并不知道对于给定的UserName，这个User是什么角色的，
那么这些信息是从哪里获取的呢？
是从一个叫做`UserDetailsService`的Service类中获取的。

可以理解成SpringSecurity首先会和UserDetailsService进行沟通，
问这个serivce我想要关于某个用户的详细信息，请你告诉我我好去做验证。
然后UserDetailsService有自己的一套逻辑（由用户自己实现代码），
告诉SpringSecurity对应的User的详细信息。

那么实际上UserDetailsService并不是和JPA绑定的，我们完全可以用其他的方式来实现它，
比如我们可以直接在把用户信息写在txt文件中进行读取，
也可以硬编码在Java代码中，或者访问LDAP中的数据等等。

本质上来说UserDetailsService和JPA是脱离的，
这里只是使用JPA来完成了一种实现方式而已。

## 2 验证流程

- SpringSecurity向UserDetailsService请求用户详细信息
- UserDetailsService会生成一个具体的UserDetails（也是一个Spring的接口）
- SpringSecurity获取到这个UserDetails之后根据预先设定好的逻辑进行验证

那我们需要做的就是以下几件事情

- 配置验证的规则：什么样的url允许什么样的用户访问
- 实现UserDetailsService：如何根据用户获取用户的详细信息
- UserDetails：这个和第二步类似，详细信息包含的内容要符合SpringSecurity的规则

## 3 利用JPA获取UserDetails

实际上如果我们做到了step2，
我们已经实现了SpringSecurity的大部分内容。

剩下的工作主要是能更动态和扩展的获取UserDetails，但是这些内容已经和SpringSecurity的验证内容无关了。