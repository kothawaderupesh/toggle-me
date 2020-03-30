package com.db.app;

import org.springframework.stereotype.Component;

@Component
public class AOPTest {

    @ToggleFeature(featureName = "test")
    public void test() {
        System.out.println("Test method called");
    }

    @ToggleFeature(featureName = "test1")
    public void test1() {
        System.out.println("Test 1 method called");
    }
}
