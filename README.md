# Verify
一个简单的 java 参数校验器

注解|描述|是否实现
---|---
@Null|被注释的元素必须为 null|否
@NotNull|被注释的元素必须不为 null|否
@AssertTrue|被注释的元素必须为 true|否
@AssertFalse|被注释的元素必须为 false|否
@Min(value)|被注释的元素必须是一个数字，其值必须大于等于指定的最小值|否
@Max(value)|被注释的元素必须是一个数字，其值必须小于等于指定的最大值|否
@DecimalMin(value)|被注释的元素必须是一个数字，其值必须大于等于指定的最小值|否
@DecimalMax(value)|被注释的元素必须是一个数字，其值必须小于等于指定的最大值|否
@Size(max, min)|被注释的元素的大小必须在指定的范围内|否
@Digits (integer, fraction)|被注释的元素必须是一个数字，其值必须在可接受的范围内|否
@Past|被注释的元素必须是一个过去的日期|否
@Future|被注释的元素必须是一个将来的日期|否
@Pattern(value)|被注释的元素必须符合指定的正则表达式|否

要求被校验的Bean类的字段必须有访问类型为public的无参数的且有返回值的方法，否则跳过该字段的校验