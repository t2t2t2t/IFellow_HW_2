package ifellow.kireeva.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TaskModel {
    private final String title;
    private final String description;
    private final List<String> labels;
    private final String environment;
    private final String priority;
    private final String fixVersion;
    private final String version;
    private final String issueType;
}
