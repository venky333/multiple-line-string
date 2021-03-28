package com.oracle.multiplelinestring.service;

import com.oracle.multiplelinestring.helper.MultipleLineStringServiceHelper;
import com.oracle.multiplelinestring.model.BuildDetails;
import com.oracle.multiplelinestring.util.Constants;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MultipleLineStringService {

    final MultipleLineStringServiceHelper multipleLineStringServiceHelper;

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
        // initialisation
        Map<Integer, HashSet<Long>> uniqueCustomerIdByContractMap = new HashMap<>();
        Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap = new HashMap<>();
        Map<String, List<Integer>> buildDurationByGeoZoneMap = new HashMap<>();

        // splits the input multiple lines into line with delimiter as space
        String[] lines = multiLines.split(Constants.SPACE_REGEX);
        List<BuildDetails> buildDetailsList = multipleLineStringServiceHelper.prepareBuildDetailsList(lines);

        buildDetailsList.forEach(bd -> {
            // preparing map with contract_id as key and HashSet of customer_id as value
            uniqueCustomerIdByContractMap.computeIfAbsent(bd.getContractId(), k -> new HashSet<>());
            uniqueCustomerIdByContractMap.get(bd.getContractId()).add(bd.getCustomerId());

            // preparing map with geo_zone as key and HashSet of customer_id as value
            uniqueCustomerIdByGeoZoneMap.computeIfAbsent(bd.getGeoZone(), k -> new HashSet<>());
            uniqueCustomerIdByGeoZoneMap.get(bd.getGeoZone()).add(bd.getCustomerId());

            // preparing map with geo_zone as key and List of build_duration as value
            buildDurationByGeoZoneMap.computeIfAbsent(bd.getGeoZone(), k -> new ArrayList<>());
            buildDurationByGeoZoneMap.get(bd.getGeoZone()).add(Integer.parseInt(bd.getBuildDuration()
                    .replace(Constants.S_CHAR, Constants.EMPTY)));
        });

        // calls helper method to print the number of unique customerId for each contract_id
        multipleLineStringServiceHelper.printUniqueCustomerIdByContract(uniqueCustomerIdByContractMap);

        // calls helper method to print the number of unique customerId for each geo_zone
        multipleLineStringServiceHelper.printUniqueCustomerIdByGeoZone(uniqueCustomerIdByGeoZoneMap);

        // calls helper method to print the average build_duration for each geo_zone
        multipleLineStringServiceHelper.printBuildDurationByGeoZone(buildDurationByGeoZoneMap);

        // calls helper method to print the list of unique customer_id for each geo_zone
        multipleLineStringServiceHelper.printUniqueCustomerListByGeoZone(uniqueCustomerIdByGeoZoneMap);
    }
}
