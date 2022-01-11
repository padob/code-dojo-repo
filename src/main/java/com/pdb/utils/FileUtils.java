package com.pdb.utils;

public class FileUtils {

    private static final String TEST_RESOURCES_MOCKS = "src/test/resources/mocks/";

    public static String getFilenamePath(String filename) {
        return TEST_RESOURCES_MOCKS + filename;
    }
}
