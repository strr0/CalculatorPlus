package com.example.calculatorplus.entity;

import java.util.List;

public class NumberVo {
    /**
     * 姓名
     */
    private String name;

    /**
     * 总金额
     */
    private Double totalMoney;

    /**
     * 记录
     */
    private List<NumberRecord> records;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<NumberRecord> getRecords() {
        return records;
    }

    public void setRecords(List<NumberRecord> records) {
        this.records = records;
    }
}
