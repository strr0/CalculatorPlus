package com.example.calculatorplus.entity;

import java.util.ArrayList;
import java.util.List;

public class ResultVo {
    private String text;
    private Integer color = 0xFFFFFFFF;
    private Double totalMoney;
    private final List<NumberRecord> records = new ArrayList<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void addRecord(NumberRecord record) {
        records.add(record);
    }

    public void clearRecord() {
        records.clear();
    }
}
