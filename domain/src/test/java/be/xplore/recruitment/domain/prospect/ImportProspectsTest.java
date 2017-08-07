package be.xplore.recruitment.domain.prospect;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ImportProspectsTest {
    @Mock
    private ProspectRepository prospectRepository;
    private ImportProspects importProspects;
    private List<String> emails = Arrays.asList(
            "AudryLauwerijssen@teleworm.us",
            "RoselieBoonman@armyspy.com",
            "OnneReerink@dayrep.com");

    @Before
    public void setup() {
        CreateProspect createProspect = new CreateProspectUseCase(prospectRepository);
        importProspects = new ImportProspectsUseCase(createProspect);
    }

    @Test
    public void parsesSingleLine() {
        String s = "Audry,Lauwerijssen,AudryLauwerijssen@teleworm.us,+320477904247";
        ImportProspectsRequest req = createRequest(s);
        importProspects.importProspects(req, result -> {
            assertThat(result.getFailed().size(), is(0));
            assertThat(result.getProspects().size(), is(1));
            ProspectResponseModel prm = result.getProspects().get(0);
            assertThat(prm.getFirstName(), is("Audry"));
            assertThat(prm.getLastName(), is("Lauwerijssen"));
            assertThat(prm.getEmail(), is("AudryLauwerijssen@teleworm.us"));
            assertThat(prm.getPhone(), is("+320477904247"));
        });
    }

    @Test
    public void parsesBadLine() {
        String s = "Katia,Eissens,KatiaEissens@armyspy.com,sqmfkjsqmfj\n"
                + "Dide,Priester,fdqsjmfkjqsf,+320482313966";
        ImportProspectsRequest req = createRequest(s);
        importProspects.importProspects(req, result -> {
            assertThat(result.getProspects().size(), is(0));
            assertThat(result.getFailed().size(), is(2));
            assertThat(result.getFailed().get(0).getReason(), is("Invalid phone number"));
            assertThat(result.getFailed().get(1).getReason(), is("Invalid e-mail"));
        });
    }

    @Test
    public void parsesMultipleLines() {
        String s = "Audry,Lauwerijssen,AudryLauwerijssen@teleworm.us,+320477904247\n"
                + "Roselie,Boonman,RoselieBoonman@armyspy.com,+320499245722\n"
                + "Onne,Reerink,OnneReerink@dayrep.com,+320493288926";
        ImportProspectsRequest req = createRequest(s);
        importProspects.importProspects(req, result -> {
            assertThat(result.getFailed().size(), is(0));
            validateEmails(result, emails);
        });
    }

    @Test
    public void parsesMultipleLinesWithBadLine() {
        String s = "Audry,Lauwerijssen,AudryLauwerijssen@teleworm.us,+320477904247\n"
                + "Roselie,Boonman,RoselieBoonman@armyspy.com,+320499245722\n"
                + "Katia,Eissens,KatiaEissens@armyspy.com,sqmfkjsqmfj\n"
                + "Onne,Reerink,OnneReerink@dayrep.com,+320493288926";
        ImportProspectsRequest req = createRequest(s);
        importProspects.importProspects(req, result -> {
            assertThat(result.getFailed().size(), is(1));
            validateEmails(result, emails);
        });
    }

    @Test
    public void parsesRecordWithoutEmail() {
        String s = "Audry,Lauwerijssen, ,+320477904247";
        ImportProspectsRequest req = createRequest(s);
        importProspects.importProspects(req, result -> {
            assertThat(result.getProspects().size(), is(1));
            assertThat(result.getFailed().size(), is(0));
            ProspectResponseModel prm = result.getProspects().get(0);
            assertThat(prm.getFirstName(), is("Audry"));
            assertThat(prm.getLastName(), is("Lauwerijssen"));
            assertNull(prm.getEmail());
            assertThat(prm.getPhone(), is("+320477904247"));
        });
    }

    @Test
    public void parsesRecordWithoutPhoneNumber() {
        String s = "Audry,Lauwerijssen, , ";
        ImportProspectsRequest req = createRequest(s);
        importProspects.importProspects(req, result -> {
            assertThat(result.getProspects().size(), is(1));
            assertThat(result.getFailed().size(), is(0));
            ProspectResponseModel prm = result.getProspects().get(0);
            assertThat(prm.getFirstName(), is("Audry"));
            assertThat(prm.getLastName(), is("Lauwerijssen"));
            assertNull(prm.getEmail());
            assertNull(prm.getPhone());
        });
    }

    private void validateEmails(ImportProspectsResponseModel response, List<String> emails) {
        List<ProspectResponseModel> prospects = response.getProspects();
        assertThat(prospects.size(), is(emails.size()));
        for (int i = 0; i < prospects.size(); i++) {
            assertThat(prospects.get(i).getEmail(), is(emails.get(i)));
        }
    }

    private ImportProspectsRequest createRequest(String content) {
        try {
            InputStream is = new ByteArrayInputStream(content.getBytes("UTF-8"));
            return new ImportProspectsRequest(is);
        } catch (Exception ex) {
            return null;
        }
    }
}
