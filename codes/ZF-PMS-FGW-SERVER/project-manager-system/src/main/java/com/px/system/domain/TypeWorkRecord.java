package com.px.system.domain;

/**
 * @author:terry
 * @create: 2024-04-19 01:23
 * @Description: 工单类型统计
 */
public class TypeWorkRecord {

    private String workType;

    private Integer totalCount;

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
