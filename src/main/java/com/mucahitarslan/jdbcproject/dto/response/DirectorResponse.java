package com.mucahitarslan.jdbcproject.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DirectorResponse{
        private String name;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        private LocalDate birthDate;

        public DirectorResponse() {
        }

        public DirectorResponse(String name, LocalDate birthDate) {
                this.name = name;
                this.birthDate = birthDate;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public LocalDate getBirthDate() {
                return birthDate;
        }

        public void setBirthDate(LocalDate birthDate) {
                this.birthDate = birthDate;
        }
}