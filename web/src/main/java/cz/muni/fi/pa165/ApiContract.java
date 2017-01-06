package cz.muni.fi.pa165;

/**
 * @author Jakub Fiser
 * @date 20/12/2016
 */
public abstract class ApiContract {
    public static final String REST = "/rest";

    public static final class BottleType {
        public static final String BASE = "/bottleType";
        public static final String ID = "/{id}";
        public static final String PATH_ID = "id";
    }

    public static final class Bottle {
        public static final String BASE = "/bottle";
        public static final String TOXIC = "/toxic";
        public static final String ID = "/{id}";
        public static final String PATH_ID = "id";
    }

    public static final class Laboratory {
        public static final String BASE = "/laboratory";
        public static final String BOTTLES = "/bottles";
    }

    public static final class Manufacturer {
        public static final String BASE = "/manufacturer";
        public static final String ID = "/{id}";
        public static final String PRODUCTION = "/{id}/production";
        public static final String BOTTLE_TYPES = "/{id}/bottleTypes";
        public static final String PATH_ID = "id";
        public static final String PARAM_DELETED = "deleted";
    }

    public static final class Store {
        public static final String BASE = "/store";
        public static final String ID = "/{id}";
        public static final String BOTTLES_NONTOXIC = "/{id}/bottles/nontoxic";
        public static final String BOTTLES_ALL = "/{id}/bottles/all";
        public static final String STORE_BY_BOTTLE_TYPE = "bottleType/{id}";
        public static final String STORE_BOTTLE_TYPES = "/{id}/bottleType";
        public static final String PATH_ID = "id";
    }

    public static final class User {
        public static final String BASE = "/user";
        public static final String MANUFACTURER = "/manufacturer";
        public static final String STORE = "/store";
    }
}
