# Validator一个简单的 java 参数校验器#使用样例1. 在要校验的类的字段上使用注解```java@ValidatorNotNullprivate String name;```2. 调用 Validator 类```javaValidator.validate(xxx);```	_校验失败会抛出 ValidatorException的，注意捕获！_#如何增加一个校验器1. 在 function 工程中新建了 Handle 类，并实现 ValidatorHandle 接口2. 新建一个注解，并在该注解上加上以下注解```java@Target(value = { ElementType.FIELD })@Retention(RetentionPolicy.RUNTIME)@Documented@Handle(handle = xxx.class)```	_xxx 处填写之前新建的 Handle 类名_3. 到此一个属于你自己的校验器已经完成啦！