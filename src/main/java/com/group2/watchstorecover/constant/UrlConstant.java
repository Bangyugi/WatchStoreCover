package com.group2.watchstorecover.constant;

public class UrlConstant {

    public static final String SEARCH = "/search";

    public static class CustomerUrl{
        public static final String PREFIX = "/api/customers";
        public static final String GET_ALL = SEARCH;
        public static final String GET_BY_ID = SEARCH + "/{customerId}";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/{customerId}";
        public static final String TEMPORARY_DELETE = "/delete/{customerId}";
        public static final String PERMANENT_DELETE = "/permanent-delete/{customerId}";
    } ;

    public static class AddressUrl{
        public static final String PREFIX = "/api/addresses";
        public static final String FIND_BY_ADDRESS_ID =SEARCH+ "/{addressId}";
        public static final String FIND_BY_CUSTOMER_ID=SEARCH+"/customer/{customerId}";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{addressId}";

    }

    public static class BlogUrl{
        public static final String PREFIX = "/api/blogs";
        public static final String GET_ALL = SEARCH;
        public static final String GET_ALL_BY_CUSTOMER = SEARCH+"/customer/{customerId}";
        public static final String GET_BY_ID = SEARCH+"/{blogId}";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{blogId}";
        public static final String TEMPORARY_DELETE = "/delete/{blogId}";

    }

}
