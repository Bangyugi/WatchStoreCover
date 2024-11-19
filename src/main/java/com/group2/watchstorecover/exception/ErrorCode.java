package com.group2.watchstorecover.exception;

import com.group2.watchstorecover.constant.ErrorResponseCode;
import com.group2.watchstorecover.constant.ErrorResponseMessage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public enum ErrorCode {
    ERR_VALID(101,"Error not define"),
    ERR_VALID_ARGUMENT(102,"Error not define"),
    ERR_ID_NOT_FOUND(ErrorResponseCode.ERR_ID_NOT_FOUND, ErrorResponseMessage.ERR_ID_NOT_FOUND),
    ERR_CUSTOMER_EMAIL_INVALID(ErrorResponseCode.Customer.ERR_CUSTOMER_EMAIL_INVALID, ErrorResponseMessage.Customer.ERR_CUSTOMER_EMAIL_INVALID),
    ERR_CUSTOMER_PHONE_INVALID(ErrorResponseCode.Customer.ERR_CUSTOMER_PHONE_INVALID, ErrorResponseMessage.Customer.ERR_CUSTOMER_PHONE_INVALID),
    EMAIL_EXISTS(ErrorResponseCode.Customer.EMAIL_EXIST,ErrorResponseMessage.Customer.EMAIL_EXIST),
    PHONE_EXISTS(ErrorResponseCode.Customer.PHONE_EXIST,ErrorResponseMessage.Customer.PHONE_EXIST),
    EMAIL_NO_CHANGE(ErrorResponseCode.Customer.EMAIL_NO_CHANGE,ErrorResponseMessage.Customer.EMAIL_NO_CHANGE),
    ERR_ADDRESS_EXISTS_FOR_CUSTOMER(ErrorResponseCode.Address.ERR_ADDRESS_EXISTS_FOR_CUSTOMER,ErrorResponseMessage.Address.ERR_ADDRESS_EXISTS_FOR_CUSTOMER),
    ERR_CUSTOMER_NOT_CHANGE(ErrorResponseCode.Customer.ERR_CUSTOMER_NOT_CHANGE,ErrorResponseMessage.Customer.ERR_CUSTOMER_NOT_CHANGE),
    CATEGORY_NOT_FOUND(ErrorResponseCode.Category.CATEGORY_NOT_FOUND,ErrorResponseMessage.Category.CATEGORY_NOT_FOUND),
    CATEGORY_EXISTS(ErrorResponseCode.Category.CATEGORY_EXISTS,ErrorResponseMessage.Category.CATEGORY_EXISTS),
    BRAND_NOT_FOUND(ErrorResponseCode.Brand.BRAND_NOT_FOUND,ErrorResponseMessage.Brand.BRAND_NOT_FOUND),
    BRAND_EXISTS(ErrorResponseCode.Brand.BRAND_EXISTS,ErrorResponseMessage.Brand.BRAND_EXISTS),
    PRODUCT_NOT_FOUND(ErrorResponseCode.Product.PRODUCT_NOT_FOUND,ErrorResponseMessage.Product.PRODUCT_NOT_FOUND),
    PRODUCT_EXISTS(ErrorResponseCode.Product.PRODUCT_EXISTS,ErrorResponseMessage.Product.PRODUCT_EXISTS),
    PRODUCT_NOT_CHANGE(ErrorResponseCode.Product.PRODUCT_NOT_CHANGE,ErrorResponseMessage.Product.PRODUCT_NOT_CHANGE),
    PRODUCT_DETAILS_NOT_FOUND(ErrorResponseCode.Product.PRODUCT_DETAILS_NOT_FOUND,ErrorResponseMessage.Product.PRODUCT_DETAILS_NOT_FOUND),
    PRODUCT_DETAILS_EXISTS(ErrorResponseCode.Product.PRODUCT_DETAILS_EXISTS,ErrorResponseMessage.Product.PRODUCT_DETAILS_EXISTS),
    CUSTOMER_NOT_FOUND(ErrorResponseCode.Customer.CUSTOMER_NOT_FOUND,ErrorResponseMessage.Customer.CUSTOMER_NOT_FOUND),
    CART_EXISTS(ErrorResponseCode.Cart.CART_EXISTS,ErrorResponseMessage.Cart.CART_EXISTS),
    CART_NOT_FOUND(ErrorResponseCode.Cart.CART_NOT_FOUND,ErrorResponseMessage.Cart.CART_NOT_FOUND),
    COMMENT_NOT_FOUND(ErrorResponseCode.Comment.COMMENT_NOT_FOUND,ErrorResponseMessage.Comment.COMMENT_NOT_FOUND)
    ;
    int code;
    String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
