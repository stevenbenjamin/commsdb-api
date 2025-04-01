package commsdb.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class ComplianceRisk extends PanacheEntity {
    public String name;
    public String suggestedEdits;
    public Long submissionId;
    public Long riskLevelId;
}

