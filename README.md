# Verify
一个简单的 java 参数校验器

#使用样例

在要校验的类的字段上使用注解
```java
@VerifyNull
private String name;
```

调用 Verify 类
```java
Verify.verify(xxx);
```

校验失败会抛出 VerifyException的，注意捕获！

#如何增加一个校验器

在 function 工程中新建了 Handle 类，并实现 VerifyHandle 接口

新建一个注解，并在该注解上加上以下注解
```java
@Target(value = { ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Handle(handle = xxx.class)
```

xxx 处填写之前新建的 Handle 类名

到此一个属于你自己的校验器已经完成啦！
