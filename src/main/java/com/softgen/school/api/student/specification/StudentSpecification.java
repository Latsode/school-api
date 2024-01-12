package com.softgen.school.api.student.specification;
import com.softgen.school.api.student.model.dao.Student;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

import java.util.Optional;


public class StudentSpecification {

    public static Specification<Student> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                Optional.ofNullable(firstName)
                        .filter(name -> !name.isEmpty())
                        .map(name -> criteriaBuilder.like(root.get("firstName"), "%" + name + "%"))
                        .orElse(null);
    }

    public static Specification<Student> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                Optional.ofNullable(lastName)
                        .filter(name -> !name.isEmpty())
                        .map(name -> criteriaBuilder.like(root.get("lastName"), "%" + name + "%"))
                        .orElse(null);
    }

    public static Specification<Student> hasPersonalNumber(String personalNumber) {
        return (root, query, criteriaBuilder) ->
                Optional.ofNullable(personalNumber)
                        .filter(number -> !number.isEmpty())
                        .map(number -> criteriaBuilder.equal(root.get("personalNumber"), number))
                        .orElse(null);
    }

    public static Specification<Student> wasBornOn(LocalDate dateOfBirth) {
        return (root, query, criteriaBuilder) ->
                Optional.ofNullable(dateOfBirth)
                        .map(date -> criteriaBuilder.equal(root.get("dateOfBirth"), date))
                        .orElse(null);
    }

    public static Specification<Student> searchByMultipleFields(String firstName, String lastName, String personalNumber, LocalDate dateOfBirth) {
        return Specification.where(hasFirstName(firstName))
                .and(hasLastName(lastName))
                .and(hasPersonalNumber(personalNumber))
                .and(wasBornOn(dateOfBirth));
    }
}
