package com.group2.watchstorecover.constant;

public class UrlConstant {

    public static final String SEARCH = "/search";

    public static class CustomerUrl{
        public static final String PREFIX = "/api/customers";
        public static final String GET_ALL = SEARCH;
        public static final String GET_BY_ID = SEARCH + "/{customerId}";
        public static final String CREATE = "";
        public static final String UPDATE = "/{customerId}";
        public static final String TEMPORARY_DELETE = "/temporary-delete/{customerId}";
        public static final String PERMANENT_DELETE = "/permanent-delete/{customerId}";
    } ;

}
