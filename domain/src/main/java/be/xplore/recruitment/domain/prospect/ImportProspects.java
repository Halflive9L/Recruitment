package be.xplore.recruitment.domain.prospect;

import java.util.function.Consumer;

public interface ImportProspects {
    void importProspects(ImportProspectsRequest request, Consumer<ImportProspectsResponseModel> consumer);
}
