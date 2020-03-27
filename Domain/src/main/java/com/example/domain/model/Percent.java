package com.example.domain.model;

import java.util.Objects;

public class Percent {
    private Category category;
    private long sum;
    private float percent;

    public Percent(){

    }
    public Percent(Category category){
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Percent)) return false;
        Percent percent1 = (Percent) o;
        return sum == percent1.sum &&
                Float.compare(percent1.percent, percent) == 0 &&
                Objects.equals(category, percent1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, sum, percent);
    }
}
