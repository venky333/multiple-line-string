package com.oracle.multiplelinestring.service;

import com.oracle.multiplelinestring.helper.MultipleLineStringServiceHelper;
import com.oracle.multiplelinestring.model.BuildDetails;
import com.oracle.multiplelinestring.util.Constants;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MultipleLineStringService {

    private final MultipleLineStringServiceHelper multipleLineStringServiceHelper;

    public MultipleLineStringService(MultipleLineStringServiceHelper multipleLineStringServiceHelper) {
        this.multipleLineStringServiceHelper = multipleLineStringServiceHelper;
    }

    /**
     * Method will process the input (multi line),
     * prepares:
     * a) Map with contract_id as key and HashSet of customer_id as value.
     * b) Map with geo_zone as key and HashSet of customer_id as value.
     * c) Map with geo_zone as key and List of build_duration as value.
     * <p>
     * Output: calls helper methods which prints the the following reports in console.
     * a) The number of unique customerId for each contract_id.
     * b) The number of unique customerId for each geo_zone.
     * c) The average build_duration for each geo_zone.
     * d) The list of unique customer_id for each geo_zone.
     *
     * @param multiLines
     */
    public void processMultiLineString(String multiLines) {
        Map<Integer, HashSet<Long>> uniqueCustomerIdByContractMap = new HashMap<>();
        Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap = new HashMap<>();
        Map<String, List<Integer>> buildDurationByGeoZoneMap = new HashMap<>();

        String[] lines = multiLines.split(Constants.SPACE_REGEX);
        List<BuildDetails> buildDetailsList = multipleLineStringServiceHelper.prepareBuildDetailsList(lines);

        buildDetailsList.forEach(buildDetails -> {
            uniqueCustomerIdByContractMap.computeIfAbsent(buildDetails.getContractId(), k -> new HashSet<>());
            uniqueCustomerIdByContractMap.get(buildDetails.getContractId()).add(buildDetails.getCustomerId());

            uniqueCustomerIdByGeoZoneMap.computeIfAbsent(buildDetails.getGeoZone(), k -> new HashSet<>());
            uniqueCustomerIdByGeoZoneMap.get(buildDetails.getGeoZone()).add(buildDetails.getCustomerId());

            buildDurationByGeoZoneMap.computeIfAbsent(buildDetails.getGeoZone(), k -> new ArrayList<>());
            buildDurationByGeoZoneMap.get(buildDetails.getGeoZone()).add(Integer.parseInt(buildDetails.getBuildDuration()
                    .replace(Constants.S_CHAR, Constants.EMPTY)));
        });

        multipleLineStringServiceHelper.printUniqueCustomerIdByContract(uniqueCustomerIdByContractMap);

        multipleLineStringServiceHelper.printUniqueCustomerIdByGeoZone(uniqueCustomerIdByGeoZoneMap);

        multipleLineStringServiceHelper.printBuildDurationByGeoZone(buildDurationByGeoZoneMap);

        multipleLineStringServiceHelper.printUniqueCustomerListByGeoZone(uniqueCustomerIdByGeoZoneMap);
    }
}
