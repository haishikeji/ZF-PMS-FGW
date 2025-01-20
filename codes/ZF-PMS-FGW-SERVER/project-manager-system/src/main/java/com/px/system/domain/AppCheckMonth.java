package com.px.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author:terry
 * @create: 2024-07-12 19:43
 * @Description:
 */

@Data
public class AppCheckMonth {

    private String id;

    private String fillFormInfo;

    private String projectId;

    private String taskName;

    private String taskNo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date taskBeginTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date taskEndTime;

    private Boolean taskStuts;

    private Boolean monthlyReportingSwitch;
}
