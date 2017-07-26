package be.xplore.recruitment.persistence.applicant;

import be.xplore.recruitment.domain.applicant.Applicant;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
