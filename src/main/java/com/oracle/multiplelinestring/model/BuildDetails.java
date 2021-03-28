package com.oracle.multiplelinestring.model;

import lombok.Data;

@Data
public class BuildDetails {
    private Long customerId;
    private int contractId;
    private String geoZone;
    private String teamCode;
    private String projectCode;
    private String buildDuration;
}
