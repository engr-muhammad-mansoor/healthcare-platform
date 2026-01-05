package com.healthcare.uman.dto.review;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class LocalRateDTO implements Serializable {

    private Integer rate;
    private Integer averagePercent;
    private Integer count;

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getAveragePercent() {
        return averagePercent;
    }

    public void setAveragePercent(Integer averagePercent) {
        this.averagePercent = averagePercent;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        LocalRateDTO localRate = (LocalRateDTO) o;

        return new EqualsBuilder().append(rate, localRate.rate).append(averagePercent, localRate.averagePercent).append(count, localRate.count).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(rate).append(averagePercent).append(count).toHashCode();
    }

    public LocalRateDTO createLocalRate(int rate, int count, int total) {
        this.setRate(rate);
        this.setCount(count);
        Double ceil = Math.ceil((count * 100) / total);
        this.setAveragePercent(ceil.intValue());
        return this;
    }
}
