package be.xplore.recruitment.domain.prospect;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ImportProspectsTest {
    private static final List<String> emails = Arrays.asList(
            "AudryLauwerijssen@teleworm.us",
            "RoselieBoonman@armyspy.com",
            "OnneReerink@dayrep.com");
    private static final String CSV_SINGLE_LINE = "Audry,Lauwerijssen,AudryLauwerijssen@teleworm.us,+320477904247";
    private static String CSV_BAD_LINES = "Katia,Eissens,KatiaEissens@armyspy.com,sqmfkjsqmfj\n"
            + "Dide,Priester,fdqsjmfkjqsf,+320482313966";
    private static String CSV_MULTI_LINE = "Audry,Lauwerijssen,AudryLauwerijssen@teleworm.us,+320477904247\n"
            + "Roselie,Boonman,RoselieBoonman@armyspy.com,+320499245722\n"
            + "Onne,Reerink,OnneReerink@dayrep.com,+320493288926";
    private static final String CSV_MULTI_LINE_WITH_BAD_LINES = "Audry,Lauwerijssen,AudryLauwerijssen@teleworm.us," +
            "+320477904247\n" + "Roselie,Boonman,RoselieBoonman@armyspy.com,+320499245722\n"
            + "Katia,Eissens,KatiaEissens@armyspy.com,sqmfkjsqmfj\n"
            + "Onne,Reerink,OnneReerink@dayrep.com,+320493288926";
    private static final String CSV_NO_EMAIL = "Audry,Lauwerijssen, ,+320477904247";
    private static final String CSV_NO_EMAIL_NO_PHONE = "Audry,Lauwerijssen, , ";
    private ImportProspects importProspects;

    @Before
    public void setup() {
        CreateProspect createProspect = new CreateProspectUseCase(new MockProspectRepo());
        importProspects = new ImportProspectsUseCase(createProspect);
    }

    @Test
    public void parsesSingleLine() {
        ImportProspectsRequest req = createRequest(CSV_SINGLE_LINE);
        importProspects.importProspects(req, result -> {
            verifyResultSize(result, 1, 0);
            verifyProspectResponseModel(result.getProspects().get(0), Prospect.builder()
                    .withFirstName("Audry")
                    .withLastName("Lauwerijssen")
                    .withEmail("AudryLauwerijssen@teleworm.us")
                    .withPhone("+320477904247")
                    .build());
        });
    }

    @Test
    public void parsesBadLine() {
        importProspects.importProspects(createRequest(CSV_BAD_LINES), result -> {
            verifyResultSize(result, 0, 2);
            assertThat(result.getFailed().get(0).getReason(), is("Invalid phone number"));
            assertThat(result.getFailed().get(1).getReason(), is("Invalid e-mail"));
        });
    }

    @Test
    public void parsesMultipleLines() {
        ImportProspectsRequest req = createRequest(CSV_MULTI_LINE);
        importProspects.importProspects(req, result -> {
            assertThat(result.getFailed().size(), is(0));
            validateEmails(result, emails);
        });
    }

    @Test
    public void parsesMultipleLinesWithBadLine() {
        ImportProspectsRequest req = createRequest(CSV_MULTI_LINE_WITH_BAD_LINES);
        importProspects.importProspects(req, result -> {
            assertThat(result.getFailed().size(), is(1));
            validateEmails(result, emails);
        });
    }

    @Test
    public void parsesRecordWithoutEmail() {
        importProspects.importProspects(createRequest(CSV_NO_EMAIL), result -> {
            verifyResultSize(result, 1, 0);
            verifyProspectResponseModel(result.getProspects().get(0), Prospect.builder()
                    .withFirstName("Audry")
                    .withLastName("Lauwerijssen")
                    .withEmail(null)
                    .withPhone("+320477904247")
                    .build());
        });
    }

    @Test
    public void parsesRecordWithoutPhoneNumber() {
        importProspects.importProspects(createRequest(CSV_NO_EMAIL_NO_PHONE), result -> {
            verifyResultSize(result, 1, 0);
            ProspectResponseModel prm = result.getProspects().get(0);
            verifyProspectResponseModel(result.getProspects().get(0), Prospect.builder()
                    .withFirstName("Audry")
                    .withLastName("Lauwerijssen")
                    .withEmail(null)
                    .withPhone(null)
                    .build());
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

    private void verifyResultSize(ImportProspectsResponseModel result, int successCount, int failCount) {
        assertThat(result.getFailed().size(), is(failCount));
        assertThat(result.getProspects().size(), is(successCount));
    }

    private void verifyProspectResponseModel(ProspectResponseModel response, Prospect expected) {
        assertThat(response.getFirstName(), is(expected.getFirstName()));
        assertThat(response.getLastName(), is(expected.getLastName()));
        assertThat(response.getEmail(), is(expected.getEmail()));
        assertThat(response.getPhone(), is(expected.getPhone()));
    }
}
