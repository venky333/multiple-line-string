package com.oracle.multiplelinestring.helper;

import com.oracle.multiplelinestring.AbstractTest;
import com.oracle.multiplelinestring.model.BuildDetails;
import com.oracle.multiplelinestring.util.Constants;
import nl.altindag.log.LogCaptor;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.powermock.reflect.Whitebox;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class MultipleLineStringServiceHelperTest extends AbstractTest {

    private static final String INPUT = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
            "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
            "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
            "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
            "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
    @InjectMocks
    MultipleLineStringServiceHelper multipleLineStringServiceHelper;

    @Test
    public void givenArrayOfStrings_whenRequestedPrepareBuildDetailsList_thenReturnsListOfBuildDetails() {
        // arrange
        String[] lines = INPUT.split(Constants.SPACE_REGEX);
        // execute
        List<BuildDetails> buildDetailsList = multipleLineStringServiceHelper.prepareBuildDetailsList(lines);
        // assert
        assertThat(buildDetailsList.size()).isEqualTo(5);
    }

    @Test(expected = NullPointerException.class)
    public void givenNull_whenRequestedPrepareBuildDetailsList_thenThrowsNullPointerException() {
        // arrange and execute
        multipleLineStringServiceHelper.prepareBuildDetailsList(null);
        // assert
        // assertion happens with expected = NullPointerException.class
    }

    @Test(expected = NullPointerException.class)
    public void givenEmptyLine_whenRequestedPrepareBuildDetailsList_thenThrowsNullPointerException() {
        // arrange
        String[] lines = new String[1];
        // execute
        multipleLineStringServiceHelper.prepareBuildDetailsList(lines);
        // assert
        // assertion happens with expected = NullPointerException.class
    }

    @Test
    public void givenUniqueCustomerIdByContractMap_whenRequestedPrintUniqueCustomerIdByContract_thenLogsTwoMessages() {
        // arrange
        String expectedMsgOne = "\n\n##################### The number of unique customerId for each contractId ##########################\n";
        String expectedMsgTwo = "Number of unique customer ids for contract id 2345 are 1";
        Map<Integer, HashSet<Long>> uniqueCustomerIdByContractMap = new HashMap<>();
        HashSet<Long> hashSet = new HashSet<>();
        hashSet.add(2343225L);
        uniqueCustomerIdByContractMap.put(2345, hashSet);
        // execute
        LogCaptor logCaptor = LogCaptor.forClass(MultipleLineStringServiceHelper.class);
        multipleLineStringServiceHelper.printUniqueCustomerIdByContract(uniqueCustomerIdByContractMap);
        // assert
        assertThat(logCaptor.getInfoLogs()).hasSize(2).containsExactly(expectedMsgOne, expectedMsgTwo);
    }

    @Test
    public void givenEmptyUniqueCustomerIdByContractMap_whenRequestedPrintUniqueCustomerIdByContract_thenLogsOneMessage() {
        // arrange
        String expectedMsgOne = "\n\n##################### The number of unique customerId for each contractId ##########################\n";
        Map<Integer, HashSet<Long>> uniqueCustomerIdByContractMap = new HashMap<>();
        // execute
        LogCaptor logCaptor = LogCaptor.forClass(MultipleLineStringServiceHelper.class);
        multipleLineStringServiceHelper.printUniqueCustomerIdByContract(uniqueCustomerIdByContractMap);
        // assert
        assertThat(logCaptor.getInfoLogs()).hasSize(1).containsExactly(expectedMsgOne);
    }

    @Test
    public void givenUniqueCustomerIdByGeoZoneMap_whenRequestedPrintUniqueCustomerIdByGeoZone_thenLogsTwoMessages() {
        // arrange
        String expectedMsgOne = "\n\n##################### The number of unique customerId for each geozone ##########################\n";
        String expectedMsgTwo = "Number of unique customer ids for geo zone us_east are 1";
        Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap = new HashMap<>();
        HashSet<Long> hashSet = new HashSet<>();
        hashSet.add(2343225L);
        uniqueCustomerIdByGeoZoneMap.put("us_east", hashSet);
        // execute
        LogCaptor logCaptor = LogCaptor.forClass(MultipleLineStringServiceHelper.class);
        multipleLineStringServiceHelper.printUniqueCustomerIdByGeoZone(uniqueCustomerIdByGeoZoneMap);
        // assert
        assertThat(logCaptor.getInfoLogs()).hasSize(2).containsExactly(expectedMsgOne, expectedMsgTwo);
    }

    @Test
    public void givenEmptyUniqueCustomerIdByGeoZoneMap_whenRequestedPrintUniqueCustomerIdByGeoZone_thenLogsOneMessage() {
        // arrange
        String expectedMsgOne = "\n\n##################### The number of unique customerId for each geozone ##########################\n";
        Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap = new HashMap<>();
        // execute
        LogCaptor logCaptor = LogCaptor.forClass(MultipleLineStringServiceHelper.class);
        multipleLineStringServiceHelper.printUniqueCustomerIdByGeoZone(uniqueCustomerIdByGeoZoneMap);
        // assert
        assertThat(logCaptor.getInfoLogs()).hasSize(1).containsExactly(expectedMsgOne);
    }

    @Test
    public void givenBuildDurationByGeoZoneMap_whenRequestedPrintBuildDurationByGeoZone_thenLogsTwoMessages() {
        // arrange
        String expectedMsgOne = "\n\n##################### The average buildduration for each geozone ##########################\n";
        String expectedMsgTwo = "Average build duration of geo zone us_east is 3445.0";
        Map<String, List<Integer>> buildDurationByGeoZoneMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(3445);
        buildDurationByGeoZoneMap.put("us_east", list);
        // execute
        LogCaptor logCaptor = LogCaptor.forClass(MultipleLineStringServiceHelper.class);
        multipleLineStringServiceHelper.printBuildDurationByGeoZone(buildDurationByGeoZoneMap);
        // assert
        assertThat(logCaptor.getInfoLogs()).hasSize(2).containsExactly(expectedMsgOne, expectedMsgTwo);
    }

    @Test
    public void givenEmptyBuildDurationByGeoZoneMap_whenRequestedPrintBuildDurationByGeoZone_thenLogsOneMessage() {
        // arrange
        String expectedMsgOne = "\n\n##################### The average buildduration for each geozone ##########################\n";
        Map<String, List<Integer>> buildDurationByGeoZoneMap = new HashMap<>();
        // execute
        LogCaptor logCaptor = LogCaptor.forClass(MultipleLineStringServiceHelper.class);
        multipleLineStringServiceHelper.printBuildDurationByGeoZone(buildDurationByGeoZoneMap);
        // assert
        assertThat(logCaptor.getInfoLogs()).hasSize(1).containsExactly(expectedMsgOne);
    }

    @Test
    public void givenUniqueCustomerIdByContractMap_whenRequestedPrintUniqueCustomerListByGeoZone_thenLogsThreeMessages() {
        // arrange
        String expectedMsgOne = "\n\n##################### The list of unique customerId for each geozone ##########################\n";
        String expectedMsgTwo = "List of unique customer id for geo zone us_east are:";
        String expectedMsgThree = " * customer id: 2343225";
        Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap = new HashMap<>();
        HashSet<Long> hashSet = new HashSet<>();
        hashSet.add(2343225L);
        uniqueCustomerIdByGeoZoneMap.put("us_east", hashSet);
        // execute
        LogCaptor logCaptor = LogCaptor.forClass(MultipleLineStringServiceHelper.class);
        multipleLineStringServiceHelper.printUniqueCustomerListByGeoZone(uniqueCustomerIdByGeoZoneMap);
        // assert
        assertThat(logCaptor.getInfoLogs()).hasSize(3).containsExactly(expectedMsgOne, expectedMsgTwo, expectedMsgThree);
    }

    @Test
    public void givenEmptyUniqueCustomerIdByContractMap_whenRequestedPrintUniqueCustomerListByGeoZone_thenLogsOneMessage() {
        // arrange
        String expectedMsgOne = "\n\n##################### The list of unique customerId for each geozone ##########################\n";
        Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap = new HashMap<>();
        // execute
        LogCaptor logCaptor = LogCaptor.forClass(MultipleLineStringServiceHelper.class);
        multipleLineStringServiceHelper.printUniqueCustomerListByGeoZone(uniqueCustomerIdByGeoZoneMap);
        // assert
        assertThat(logCaptor.getInfoLogs()).hasSize(1).containsExactly(expectedMsgOne);
    }

    @Test(expected = NullPointerException.class)
    public void givenNull_whenRequestedPrepareBuildDetails_thenThrowsNullPointerException() throws Exception {
        // arrange and execute
        Whitebox.invokeMethod(multipleLineStringServiceHelper, "prepareBuildDetails", null);
        // assert
        // assertion happens with expected = NullPointerException.class
    }

    @Test
    public void givenSixAttributes_whenRequestedPrepareBuildDetails_thenReturnsBuildDetailsInstance() throws Exception {
        // arrange
        String line = "2343225,2345,us_east,RedTeam,ProjectApple,3445s";
        String[] attributes = line.split(Constants.COMMA);
        // execute
        BuildDetails buildDetails = Whitebox.invokeMethod(multipleLineStringServiceHelper, "prepareBuildDetails", (Object) attributes);
        // assert
        assertThat(buildDetails).isNotNull().isInstanceOf(BuildDetails.class);
    }
}
