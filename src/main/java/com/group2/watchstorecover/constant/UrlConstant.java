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

    public static class CategoryUrl{
        public static final String PREFIX = "/api/categories";
        public static final String GET_ALL = SEARCH;
        public static final String GET_BY_ID = SEARCH+"/{categoryId}";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{categoryId}";
        public static final String DELETE = "/delete/{categoryId}";
        public static final String ADD_CATEGORY_TO_PRODUCT = "/add-category-for-product";
        public static final String REMOVE_CATEGORY_FROM_PRODUCT = "/remove-category-from-product/{categoryId}/{productId}";
    }

    public static class BrandUrl{
        public static final String PREFIX = "/api/brands";
        public static final String GET_ALL = SEARCH;
        public static final String GET_BY_ID = SEARCH + "/{brandId}";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{brandId}";
        public static final String DELETE = "/delete/{brandId}";
    }

    public static class ProductUrl{
        public static final String PREFIX = "/api/products";
        public static final String GET_ALL =SEARCH;
        public static final String GET_BY_ID = SEARCH + "/{productId}";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{productId}";
        public static final String DELETE = "/delete/{productId}";
        public static final String GET_BY_NAME =SEARCH + "/name/{productName}";
    }

    public static class ProductDetailsUrl{
        public static final String PREFIX = "/api/product-details";
        public static final String GET_ALL =SEARCH;
        public static final String GET_BY_ID = SEARCH + "/{productDetailsId}";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{productDetailsId}";
        public static final String GET_BY_PRODUCT_ID =SEARCH + "/product/{productId}";
    }

    public static class CartUrl{
        public static final String PREFIX = "/api/carts";
        public static final String GET_BY_ID = SEARCH + "/{customerId}/{productId}";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update";
        public static final String DELETE = "/delete/{customerId}/{productId}";
        public static final String GET_BY_CUSTOMER_ID=SEARCH+"/customer/{customerId}";
        public static final String GET_BY_PRODUCT_ID=SEARCH+"/product/{productId}";
    }

    public static class CommentUrl{
        public static final String PREFIX = "/api/comments";
        public static final String GET_BY_ID =SEARCH + "/{commentId}";
        public static final String GET_BY_PRODUCT_ID=SEARCH+"/product/{productId}";
        public static final String Get_BY_COMMENT_ID=SEARCH+"/parent/{commentParentId}";
        public static final String GET_BY_CUSTOMER_ID=SEARCH+"/customer/{customerId}";
        public static final String CREATE = "/create";
        public static final String UPDATE = "/update/{commentId}";
        public static final String DELETE = "/delete/{commentId}";

    }

}
