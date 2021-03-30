package com.oracle.multiplelinestring.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderClassName = "BuildDetailsBuilder", toBuilder = true)
@JsonDeserialize(builder = BuildDetails.BuildDetailsBuilder.class)
public class BuildDetails {
    private Long customerId;
    private int contractId;
    private String geoZone;
    private String teamCode;
    private String projectCode;
    private String buildDuration;

    @JsonPOJOBuilder(withPrefix = "")
    public static class BuildDetailsBuilder {
    }
}
