package com.university.accommodationmanager.domain;

import com.university.accommodationmanager.domain.*;
import com.university.accommodationmanager.response.JwtResponse;
import com.university.accommodationmanager.response.MessageResponse;
import org.junit.Test;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class DomainTest {
    @Test
    public void accomodationTest(){
        PojoClass pojoclass=PojoClassFactory.getPojoClass(Accomodation.class);
        Validator validator=ValidatorBuilder
                .create()
                .with(new SetterMustExistRule())
                .with(new GetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();
        validator.validate(pojoclass);
    }

    @Test
    public void databaseSequenceTest(){
        PojoClass pojoclass=PojoClassFactory.getPojoClass(DatabaseSequence.class);
        Validator validator=ValidatorBuilder
                .create()
                .with(new SetterMustExistRule())
                .with(new GetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();
        validator.validate(pojoclass);
    }

    @Test
    public void loginRequestTest(){
        PojoClass pojoclass=PojoClassFactory.getPojoClass(LoginRequest.class);
        Validator validator=ValidatorBuilder
                .create()
                .with(new SetterMustExistRule())
                .with(new GetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();
        validator.validate(pojoclass);
    }

    @Test
    public void roomateTest(){
        PojoClass pojoclass=PojoClassFactory.getPojoClass(Roommate.class);
        Validator validator=ValidatorBuilder
                .create()
                .with(new SetterMustExistRule())
                .with(new GetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();
        validator.validate(pojoclass);
    }

    @Test
    public void userTest(){
        PojoClass pojoclass=PojoClassFactory.getPojoClass(User.class);
        Validator validator=ValidatorBuilder
                .create()
                .with(new SetterMustExistRule())
                .with(new GetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();
        validator.validate(pojoclass);
    }

    @Test
    public void messageResponseTest(){
        PojoClass pojoclass=PojoClassFactory.getPojoClass(MessageResponse.class);
        Validator validator=ValidatorBuilder
                .create()
                .with(new SetterMustExistRule())
                .with(new GetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();
        validator.validate(pojoclass);
    }

}
