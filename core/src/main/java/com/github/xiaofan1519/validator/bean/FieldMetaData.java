package com.github.xiaofan1519.validator.bean;

import java.lang.annotation.Annotation;

import com.github.xiaofan1519.validator.handle.ValidatorHandle;

/**
 * 被校验字段的元数据
 * @author Fan
 * 
 * 2017年8月14日
 * 
 */
public class FieldMetaData {
    
    /**
     * 被校验字段的名称
     */
    private String name;
    
    /**
     * 被校验字段的值
     */
    private Object value;
    
    /**
     * 错误提示信息，为空则使用注解中的name字段填充
     */
    private String message;
    
    /**
     * 对应的约束
     */
    private Class<? extends Annotation> constraint;
    
    /**
     * 处理器
     */
    private Class<? extends ValidatorHandle> handle;
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return the constraint
     */
    public Class<? extends Annotation> getConstraint() {
        return constraint;
    }

    /**
     * @param constraint the constraint to set
     */
    public void setConstraint(Class<? extends Annotation> constraint) {
        this.constraint = constraint;
    }

    /**
     * @return the handle
     */
    public Class<? extends ValidatorHandle> getHandle() {
        return handle;
    }

    /**
     * @param handle the handle to set
     */
    public void setHandle(Class<? extends ValidatorHandle> handle) {
        this.handle = handle;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((constraint == null) ? 0 : constraint.hashCode());
        result = prime * result + ((handle == null) ? 0 : handle.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FieldMetaData other = (FieldMetaData) obj;
        if (constraint == null) {
            if (other.constraint != null)
                return false;
        }
        else if (!constraint.equals(other.constraint))
            return false;
        if (handle == null) {
            if (other.handle != null)
                return false;
        }
        else if (!handle.equals(other.handle))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        }
        else if (!message.equals(other.message))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (value == null) {
            if (other.value != null)
                return false;
        }
        else if (!value.equals(other.value))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FieldMetaData [name=" + name + ", value=" + value + ", message=" + message + ", constraint="
                + constraint + ", handle=" + handle + "]";
    }
    
}
