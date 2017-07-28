package be.xplore.recruitment.domain.prospect;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
public interface ReadProspect {

    void readAllProspects(ReadAllProspectsResponse response);

    void readProspectById(ReadProspectRequest request, ReadProspectResponse response);
}
