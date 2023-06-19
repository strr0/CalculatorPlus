package com.example.calculatorplus.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MemberRecord {
    /**
     * 主键
     */
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * 状态
     */
    private String state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
