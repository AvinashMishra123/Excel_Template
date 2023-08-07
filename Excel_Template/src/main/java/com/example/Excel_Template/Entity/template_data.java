package com.example.Excel_Template.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class template_data {
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        private String organization;
        private String firstName;
        private String lastName;
        private String dateOfBirth;
        private String city;

        public template_data() {
                this.id = id;
                this.organization = organization;
                this.firstName = firstName;
                this.lastName = lastName;
                this.dateOfBirth = dateOfBirth;
                this.city = city;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getOrganization() {
                return organization;
        }

        public void setOrganization(String organization) {
                this.organization = organization;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getDateOfBirth() {
                return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
                this.dateOfBirth = dateOfBirth;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }
}


