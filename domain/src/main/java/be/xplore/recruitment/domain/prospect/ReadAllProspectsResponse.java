package be.xplore.recruitment.domain.prospect;

import java.util.List;

/**
 * @author Lander
 * @since 27/07/2017
 */
public interface ReadAllProspectsResponse {
    void onResponse(List<Prospect> prospects);
}
