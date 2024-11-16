package com.group2.watchstorecover.constant;

public class ErrorResponseMessage {
    public final static String ERR_ID_NOT_FOUND= "Entity not found with id";
    public static class Customer{
        public final static String ERR_CUSTOMER_EMAIL_INVALID= "Invalid email address";
        public final static String ERR_CUSTOMER_PHONE_INVALID= "Invalid phone number";
        public final static String EMAIL_EXIST = "Email already exists";
        public final static String PHONE_EXIST = "Phone number already exists";
        public final static String EMAIL_NO_CHANGE = "Email cannot be changed";
    }
}
