package be.xplore.recruitment.domain.prospect;

import be.xplore.recruitment.domain.exception.NotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Stijn Schack
 * @since 7/26/2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadProspectTest {
    private ReadProspect useCase;

    private List<Prospect> mockProspects;

    @Before
    public void initUseCase() {
        MockProspectRepo repository = new MockProspectRepo();
        mockProspects = repository.mockProspects;
        useCase = new ReadProspectUseCase(repository);
    }

    @Test
    public void testReadAllProspects() {
        List<Prospect> prospectResponse = new ArrayList<>();
        useCase.readAllProspects(response -> {
            prospectResponse.addAll(getProspectListFromResponseModelList(response));
        });
        assertThat(mockProspects, is(prospectResponse));
    }

    @Test
    public void testReadProspectById() {
        ReadProspectRequest request = getRequestFromProspect(Prospect.builder().withId(1).build());
        final Prospect[] responseProspect = new Prospect[1];
        useCase.readProspectById(request, prospectResponseModel -> {
            responseProspect[0] = getProspectFromProspectResponseModel(prospectResponseModel);
        });
        assertEquals(responseProspect[0], mockProspects.get(0));
    }

    @Test(expected = NotFoundException.class)
    public void testReadProspectById_IdDoesNotExist() {
        ReadProspectRequest request = getRequestFromProspect(Prospect.builder().withId(500).build());
        useCase.readProspectById(request, prospectResponseModel -> {
        });
    }

    @Test
    public void testReadProspectsByParam() {
        ReadProspectRequest request = getRequestFromProspect(Prospect.builder().withFirstName("leeroy").build());
        useCase.readProspectsByParam(request, responseModel -> {
            assertEquals(1, responseModel.size());
            assertEquals(mockProspects.get(1), getProspectFromProspectResponseModel(responseModel.get(0)));
        });
    }

    @Ignore
    private ReadProspectRequest getRequestFromProspect(Prospect prospect) {
        ReadProspectRequest request = new ReadProspectRequest();
        request.prospectId = prospect.getProspectId();
        request.firstName = prospect.getFirstName();
        request.lastName = prospect.getLastName();
        request.email = prospect.getEmail();
        request.phone = prospect.getPhone();
        return request;
    }

    @Ignore
    private List<Prospect> getProspectListFromResponseModelList(List<ProspectResponseModel> responseModels) {
        List<Prospect> prospects = new ArrayList<>();
        for (ProspectResponseModel responseModel : responseModels) {
            prospects.add(getProspectFromProspectResponseModel(responseModel));
        }

        return prospects;
    }

    @Ignore
    private Prospect getProspectFromProspectResponseModel(ProspectResponseModel responseModel) {
        return Prospect.builder()
                .withId(responseModel.getProspectId())
                .withFirstName(responseModel.getFirstName())
                .withLastName(responseModel.getLastName())
                .withEmail(responseModel.getEmail())
                .withPhone(responseModel.getPhone())
                .build();
    }
}
