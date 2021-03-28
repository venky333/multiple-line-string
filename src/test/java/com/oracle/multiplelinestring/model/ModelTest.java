package com.oracle.multiplelinestring.model;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ModelTest {

    private static final int EXPECTED_CLASS_COUNT = 1;
    private static final String MODEL_PACKAGE = "com.oracle.multiplelinestring.model";

    @Test
    public void ensureExpectedPojoCount() {
        List<PojoClass> pojoClasses = PojoClassFactory.getPojoClasses(MODEL_PACKAGE, new FilterPackageInfo())
                .stream()
                .filter(pojoClass -> !pojoClass.getName().toLowerCase().contains("builder"))
                .filter(pojoClass -> !pojoClass.getName().toLowerCase().contains("test"))
                .collect(Collectors.toList());
        assertThat(pojoClasses.size()).isEqualTo(EXPECTED_CLASS_COUNT);
    }

    @Test
    public void testPojoStructureAndBehavior() {
        Validator validator = ValidatorBuilder.create()
                .with(new SetterTester())
                .with(new GetterTester())
                .build();

        validator.validate(MODEL_PACKAGE, new FilterPackageInfo());
    }
}
