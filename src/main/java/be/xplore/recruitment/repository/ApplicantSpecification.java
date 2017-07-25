package be.xplore.recruitment.repository;


import be.xplore.recruitment.domain.model.Applicant;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by Lander on 20/07/2017.
 */
public class ApplicantSpecification {
    public static Specification<Applicant> hasFirstName(String firstName) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("firstName")), (firstName).toLowerCase());
    }

    public static Specification<Applicant> hasLastName(String lastName) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("lastName")), (lastName).toLowerCase());
    }

    public static Specification<Applicant> hasEmail(String email) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("email")), email.toLowerCase());
    }

    public static Specification<Applicant> hasPhone(String phone) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("phone")), phone.toLowerCase());
    }

    public static Specification<Applicant> hasEducation(String education) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("education")), education.toLowerCase());
    }

    public static Specification<Applicant> hasAdress(String address) {
        return (root, query, cb) -> cb.equal(cb.lower(root.get("address")), address.toLowerCase());
    }

    public static Specification<Applicant> hasDateOfBirth(String dateOfBirth) {
        return (root, query, cb) -> {
            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
            java.util.Date date = null;
            try {
                date = formatter.parse(dateOfBirth);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, 1);
            Date lowDate = (Date)cal.getTime();
            cal.setTime(date);
            cal.add(Calendar.DATE, -1);
            Date highDate = (Date)cal.getTime();
            return cb.between(root.get("dateOfBirth"), lowDate, highDate);
        };
    }


}
