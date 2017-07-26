package be.xplore.recruitment.persistence.applicant;

import be.xplore.recruitment.domain.applicant.Applicant;
import be.xplore.recruitment.domain.applicant.ApplicantRepository;

import java.util.List;

/**
 * Created by Lander on 26/07/2017.
 */
public class ApplicantRepoJpa implements ApplicantRepository {
    @Override
    public List<Applicant> findAll() {
        return null;
    }
}
