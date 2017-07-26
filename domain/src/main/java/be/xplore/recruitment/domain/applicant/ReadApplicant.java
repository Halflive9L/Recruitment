package be.xplore.recruitment.domain.applicant;

import java.util.List;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface ReadApplicant {

    List<Applicant> readAllApplicants();

    Applicant readApplicantById(long id);
}
