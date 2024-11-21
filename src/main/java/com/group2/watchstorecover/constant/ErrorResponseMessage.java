package com.group2.watchstorecover.constant;

public class ErrorResponseMessage {
    public final static String ERR_ID_NOT_FOUND= "Entity not found ";
    public static class Customer{
        public final static String ERR_CUSTOMER_EMAIL_INVALID= "Invalid email address";
        public final static String ERR_CUSTOMER_PHONE_INVALID= "Invalid phone number";
        public final static String EMAIL_EXIST = "Email already exists";
        public final static String PHONE_EXIST = "Phone number already exists";
        public final static String EMAIL_NO_CHANGE = "Email cannot be changed";
        public final static String ERR_CUSTOMER_NOT_CHANGE = "Customer cannot be changed";
        public final static String CUSTOMER_NOT_FOUND = "Customer not found";
    }
    public static class Address{
        public final static String ERR_ADDRESS_EXISTS_FOR_CUSTOMER = "Address already exists for customer";
    }

    public static class Category{
        public final static String CATEGORY_EXISTS = "Category already exists";
        public final static String CATEGORY_NOT_FOUND = "Category not found";
    }

    public static class Brand {
        public final static String BRAND_EXISTS = "Brand already exists";
        public final static String BRAND_NOT_FOUND = "Brand not found";
    }

    public static class Product{
        public final static String PRODUCT_EXISTS ="Product already exists";
        public final static String PRODUCT_NOT_FOUND="Product not found";
        public final static String PRODUCT_NOT_CHANGE="Product cannot be changed";
        public final static String PRODUCT_DETAILS_NOT_FOUND="This product does not have product details";
        public final static String PRODUCT_DETAILS_EXISTS="Product details already exists for this product";
    }

    public static class Cart{
        public final static String CART_EXISTS="Cart already exists";
        public final static String CART_NOT_FOUND="Cart not found";
    }

    public static class Comment{
        public final static String COMMENT_EXISTS="Comment already exists";
        public final static String COMMENT_NOT_FOUND="Comment not found";
    }

    public static class Account{
        public final static String ACCOUNT_ALREADY_EXISTS="Account already exists";
        public final static String ACCOUNT_NOT_FOUND="Account not found";
        public final static String ACCOUNT_UNAUTHENTICATED="Account is not authenticated";
        public final static String ACCOUNT_UNAUTHORIZED="Account is not authorized";
    }

    public static class Role{
        public final static String ROLE_ALREADY_EXISTS="Role already exists";
        public final static String ROLE_NOT_FOUND="Role not found";
    }
    public static class Token{
        public final static String TOKEN_EXPIRED="Token is expired";
    }
}
