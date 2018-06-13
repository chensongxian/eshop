package com.csx.eshop.inventory.model;

/**
 * @author csx
 * @Package com.csx.eshop.inventory.model
 * @Description: TODO
 * @date 2018/5/21 0021
 */

public class User {
    /**用户姓名*/
    private String name;
    /**用户年龄*/
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
