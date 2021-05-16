package edu.sdccd.cisc191.c;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerResponse {
        private Integer id;
        private String firstName;
        private String lastName;

        @JsonIgnore
        private static final ObjectMapper objectMapper = new ObjectMapper();
        public static String toJSON(edu.sdccd.cisc191.c.CustomerResponse customer) throws Exception {
            return objectMapper.writeValueAsString(customer);
        }
        public static edu.sdccd.cisc191.c.CustomerResponse fromJSON(String input) throws Exception{
            return objectMapper.readValue(input, edu.sdccd.cisc191.c.CustomerResponse.class);
        }
        protected CustomerResponse() {}

        public CustomerResponse(Integer id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return String.format(
                    "Customer[id=%d, firstName='%s', lastName='%s']",
                    id, firstName, lastName);
        }

        public Integer getId() {
            return id;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }
    