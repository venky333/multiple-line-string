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

    /**
     * Prepares BuildDetails list with array of lines as input
     *
     * @param lines
     * @return
     */
    public List<BuildDetails> prepareBuildDetailsList(String[] lines) {
        List<BuildDetails> buildDetailsList = new ArrayList<>();
        for (String line : lines) {
            buildDetailsList.add(prepareBuildDetails(line.split(Constants.COMMA)));
        }
        return buildDetailsList;
    }

    /**
     * Prints number of unique customer id's for each contract id
     *
     * @param uniqueCustomerIdByContractMap
     */
    public void printUniqueCustomerIdByContract(Map<Integer, HashSet<Long>> uniqueCustomerIdByContractMap) {
        log.info("\n\n##################### The number of unique customerId for each contractId ##########################\n");
        uniqueCustomerIdByContractMap.forEach((contractId, customerIdSet) ->
                log.info("Number of unique customer ids for contract id {} are {}",
                        contractId, customerIdSet.stream().count()));
    }

    /**
     * Prints number of unique customer id's for each geo zone
     *
     * @param uniqueCustomerIdByGeoZoneMap
     */
    public void printUniqueCustomerIdByGeoZone(Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap) {
        log.info("\n\n##################### The number of unique customerId for each geozone ##########################\n");
        uniqueCustomerIdByGeoZoneMap.forEach((geoZone, customerIdSet) ->
                log.info("Number of unique customer ids for geo zone {} are {}",
                        geoZone, customerIdSet.stream().count()));
    }

    /**
     * Prints average build duration for each geo zone
     *
     * @param buildDurationByGeoZoneMap
     */
    public void printBuildDurationByGeoZone(Map<String, List<Integer>> buildDurationByGeoZoneMap) {
        log.info("\n\n##################### The average buildduration for each geozone ##########################\n");
        buildDurationByGeoZoneMap.forEach((geoZone, bdList) ->
                log.info("Average build duration of geo zone {} is {}",
                        geoZone, bdList.stream().mapToDouble(a -> a).average().getAsDouble()));
    }

    /**
     * Prints list of unique customer id's for each geo zone
     *
     * @param uniqueCustomerIdByGeoZoneMap
     */
    public void printUniqueCustomerListByGeoZone(Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap) {
        log.info("\n\n##################### The list of unique customerId for each geozone ##########################\n");
        uniqueCustomerIdByGeoZoneMap.forEach((geoZone, customerIdSet) -> {
            log.info("List of unique customer id for geo zone {} are:", geoZone);
            customerIdSet.forEach(customerId -> log.info(" * customer id: {}", customerId));
        });
    }

    /**
     * Prepares build details from the array of attributes as input
     *
     * @param attributes
     * @return BuildDetails
     */
    private BuildDetails prepareBuildDetails(String[] attributes) {
        BuildDetails buildDetails = new BuildDetails();
        buildDetails.setCustomerId(Long.parseLong(attributes[0]));
        buildDetails.setContractId(Integer.parseInt(attributes[1]));
        buildDetails.setGeoZone(attributes[2]);
        buildDetails.setTeamCode(attributes[3]);
        buildDetails.setProjectCode(attributes[4]);
        buildDetails.setBuildDuration(attributes[5]);
        return buildDetails;
    }
}
