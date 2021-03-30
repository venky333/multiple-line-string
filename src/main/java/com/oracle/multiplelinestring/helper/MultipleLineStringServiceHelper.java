package com.oracle.multiplelinestring.helper;

import com.oracle.multiplelinestring.model.BuildDetails;
import com.oracle.multiplelinestring.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class MultipleLineStringServiceHelper {

    private static void logInfo(final String message) {
        log.info("");
        log.info("--------------------------------------");
        log.info("{}", message);
        log.info("--------------------------------------");
    }

    public List<BuildDetails> prepareBuildDetailsList(String[] lines) {
        List<BuildDetails> buildDetailsList = new ArrayList<>();
        for (String line : lines) {
            buildDetailsList.add(prepareBuildDetails(line.split(Constants.COMMA)));
        }
        return buildDetailsList;
    }

    public void printUniqueCustomerIdByContract(Map<Integer, HashSet<Long>> uniqueCustomerIdByContractMap) {
        logInfo("Contract id\t# unique customers");
        uniqueCustomerIdByContractMap.forEach((contractId, customerIds) -> log.info("{}\t\t{}", contractId, customerIds.size()));
    }

    public void printUniqueCustomerIdByGeoZone(Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap) {
        logInfo("Geo Zone\t# unique customers");
        uniqueCustomerIdByGeoZoneMap.forEach((geoZone, customerIdSet) -> log.info("{}\t{}", geoZone, customerIdSet.size()));
    }

    public void printBuildDurationByGeoZone(Map<String, List<Integer>> buildDurationByGeoZoneMap) {
        logInfo("Geo Zone\tAverage build duration");
        buildDurationByGeoZoneMap.forEach((geoZone, buildDurationList) ->
                log.info("{}\t{}", geoZone, buildDurationList.stream().mapToDouble(duration -> duration).average().getAsDouble()));
    }

    public void printUniqueCustomerListByGeoZone(Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap) {
        logInfo("Geo Zone\tUnique customer ids");
        uniqueCustomerIdByGeoZoneMap.forEach((geoZone, customerIdSet) -> log.info("{}\t{}", geoZone, customerIdSet));
    }

    private BuildDetails prepareBuildDetails(String[] attributes) {
        return BuildDetails.builder()
                .customerId(Long.parseLong(attributes[0]))
                .contractId(Integer.parseInt(attributes[1]))
                .geoZone(attributes[2])
                .teamCode(attributes[3])
                .projectCode(attributes[4])
                .buildDuration(attributes[5])
                .build();
    }
}
