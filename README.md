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

校验注解必须有一个名叫 name 的方法，否则会抛出异常
```java
String name() default "没有指定Handle";
```

xxx 处填写之前新建的 Handle 类名

到此一个属于你自己的校验器已经完成啦！

#使用 lambda 完成单元测试
```java
UnitTestUtil.test(() -> {
	Verify.verify(bean);
}, true);
```

当需要对抛出的异常进行处理时
```java
UnitTestUtil.test(()-> {
    Verify.verify(bean);
}, (e) -> {
    VerifyException ex = null;
    if (!(e instanceof VerifyException))
    {
        return false;
    }
    
    ex = (VerifyException)e;
    if (ex.getError().containsKey("name"))
    {
        return true;
    }
    
    return false;
});
```
