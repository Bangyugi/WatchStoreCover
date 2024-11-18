package com.group2.watchstorecover.constant;

public class ErrorResponseCode {
    public final static int ERR_ID_NOT_FOUND = 404;
    public static class Customer {
        public final static int ERR_CUSTOMER_EMAIL_INVALID = 1001;
        public final static int ERR_CUSTOMER_PHONE_INVALID = 1002;
        public final static int EMAIL_EXIST = 1003;
        public final static int PHONE_EXIST = 1004;
        public final static int EMAIL_NO_CHANGE = 1005;
        public final static int ERR_CUSTOMER_NOT_CHANGE = 1006;
    }
    public static class Address{
        public final static int ERR_ADDRESS_EXISTS_FOR_CUSTOMER = 2001;
    }
}
