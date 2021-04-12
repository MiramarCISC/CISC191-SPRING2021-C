package edu.sdccd.cisc191.c;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerRequest {
        private Integer id;

        @JsonIgnore
        private static final ObjectMapper objectMapper = new ObjectMapper();
        public static String toJSON(edu.sdccd.cisc191.c.CustomerRequest customer) throws Exception {
            return objectMapper.writeValueAsString(customer);
        }
        public static edu.sdccd.cisc191.c.CustomerRequest fromJSON(String input) throws Exception{
            return objectMapper.readValue(input, edu.sdccd.cisc191.c.CustomerRequest.class);
        }
        protected CustomerRequest() {}

        public CustomerRequest(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return String.format(
                    "Customer[id=%d]",
                    id);
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
