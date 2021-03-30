package com.oracle.multiplelinestring.service;

import com.oracle.multiplelinestring.AbstractTest;
import com.oracle.multiplelinestring.helper.MultipleLineStringServiceHelper;
import com.oracle.multiplelinestring.model.BuildDetails;
import com.oracle.multiplelinestring.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MultipleLineStringServiceTest extends AbstractTest {

    private static final String INPUT = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
            "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
            "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
            "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
            "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";
    @Mock
    MultipleLineStringServiceHelper multipleLineStringServiceHelper;
    @InjectMocks
    MultipleLineStringService multipleLineStringService;

    @Before
    public void init() {
        multipleLineStringService = new MultipleLineStringService(multipleLineStringServiceHelper);
    }

    @Test
    public void givenMultipleLineInput_whenRequestedProcessMultiLineString_thenVerifyPrepareBuildDetailsListInMultipleLineStringServiceHelperIsCalled() {
        // arrange
        String[] lines = INPUT.split(Constants.SPACE_REGEX);
        // execute
        multipleLineStringService.processMultiLineString(INPUT);
        // assert
        verify(multipleLineStringServiceHelper).prepareBuildDetailsList(lines);
    }

    @Test
    public void givenMultipleLineInput_whenRequestedProcessMultiLineString_thenVerifyPrintUniqueCustomerIdByContractInMultipleLineStringServiceHelperIsCalled() {
        // arrange
        String[] lines = INPUT.split(Constants.SPACE_REGEX);
        Map<Integer, HashSet<Long>> uniqueCustomerIdByContractMap = new HashMap<>();
        HashSet<Long> hashSet = new HashSet<>();
        hashSet.add(2343225L);
        uniqueCustomerIdByContractMap.put(2345, hashSet);
        when(multipleLineStringServiceHelper.prepareBuildDetailsList(lines))
                .thenReturn(Collections.singletonList(getBuildDetails()));
        // execute
        multipleLineStringService.processMultiLineString(INPUT);
        // assert
        verify(multipleLineStringServiceHelper).printUniqueCustomerIdByContract(uniqueCustomerIdByContractMap);
    }

    @Test
    public void givenMultipleLineInput_whenRequestedProcessMultiLineString_thenVerifyPrintUniqueCustomerIdByGeoZoneInMultipleLineStringServiceHelperIsCalled() {
        // arrange
        String[] lines = INPUT.split(Constants.SPACE_REGEX);
        Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap = new HashMap<>();
        HashSet<Long> hashSet = new HashSet<>();
        hashSet.add(2343225L);
        uniqueCustomerIdByGeoZoneMap.put("us_east", hashSet);
        when(multipleLineStringServiceHelper.prepareBuildDetailsList(lines))
                .thenReturn(Collections.singletonList(getBuildDetails()));
        // execute
        multipleLineStringService.processMultiLineString(INPUT);
        // assert
        verify(multipleLineStringServiceHelper).printUniqueCustomerIdByGeoZone(uniqueCustomerIdByGeoZoneMap);
    }

    @Test
    public void givenMultipleLineInput_whenRequestedProcessMultiLineString_thenVerifyPrintBuildDurationByGeoZoneInMultipleLineStringServiceHelperIsCalled() {
        // arrange
        String[] lines = INPUT.split(Constants.SPACE_REGEX);
        Map<String, List<Integer>> buildDurationByGeoZoneMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(3445);
        buildDurationByGeoZoneMap.put("us_east", list);
        when(multipleLineStringServiceHelper.prepareBuildDetailsList(lines))
                .thenReturn(Collections.singletonList(getBuildDetails()));
        // execute
        multipleLineStringService.processMultiLineString(INPUT);
        // assert
        verify(multipleLineStringServiceHelper).printBuildDurationByGeoZone(buildDurationByGeoZoneMap);
    }

    @Test
    public void givenMultipleLineInput_whenRequestedProcessMultiLineString_thenVerifyPrintUniqueCustomerListByGeoZoneInMultipleLineStringServiceHelperIsCalled() {
        // arrange
        String[] lines = INPUT.split(Constants.SPACE_REGEX);
        Map<String, HashSet<Long>> uniqueCustomerIdByGeoZoneMap = new HashMap<>();
        HashSet<Long> hashSet = new HashSet<>();
        hashSet.add(2343225L);
        uniqueCustomerIdByGeoZoneMap.put("us_east", hashSet);
        when(multipleLineStringServiceHelper.prepareBuildDetailsList(lines))
                .thenReturn(Collections.singletonList(getBuildDetails()));
        // execute
        multipleLineStringService.processMultiLineString(INPUT);
        // assert
        verify(multipleLineStringServiceHelper).printUniqueCustomerListByGeoZone(uniqueCustomerIdByGeoZoneMap);
    }

    // data
    private BuildDetails getBuildDetails() {
        return BuildDetails.builder()
                .customerId(2343225L)
                .contractId(2345)
                .geoZone("us_east")
                .teamCode("RedTeam")
                .projectCode("ProjectApple")
                .buildDuration("3445s")
                .build();
    }
}
