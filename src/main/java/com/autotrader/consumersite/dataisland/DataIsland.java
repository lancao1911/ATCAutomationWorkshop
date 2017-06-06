package com.autotrader.consumersite.dataisland;

/**
 * Created by nghiatrongtran on 05/23/2017.
 */
public interface DataIsland {

    enum BirfAttribute {
        PAGE_ID("pg"),
        PAGE_INSTANCE("pg_inst");

        private String attribute;

        BirfAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getAttribute() {
            return attribute;
        }
    }
}
