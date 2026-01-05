package com.healthcare.uman.model.conversation;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class KeywordsResult implements Serializable {
    private List<String> foundKeywords;
    private List<String> nonKeywords;

    public KeywordsResult(List<String> foundKeywords, List<String> nonKeywords) {
        this.foundKeywords = foundKeywords;
        this.nonKeywords = nonKeywords;
    }

    public List<String> getFoundKeywords() {
        return foundKeywords;
    }

    public List<String> getNonKeywords() {
        return nonKeywords;
    }

    public void setFoundKeywords(List<String> foundKeywords) {
        this.foundKeywords = foundKeywords;
    }

    public void setNonKeywords(List<String> nonKeywords) {
        this.nonKeywords = nonKeywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        KeywordsResult that = (KeywordsResult) o;

        return new EqualsBuilder().append(foundKeywords, that.foundKeywords).append(nonKeywords, that.nonKeywords).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(foundKeywords).append(nonKeywords).toHashCode();
    }
}
