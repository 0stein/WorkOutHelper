package com.portal2moon.workouthelper.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Builder
public class DailyWorkOutLog {
    @Id
    @GeneratedValue
    private final Long Id;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private final User user;

    @OneToMany(mappedBy = "dailyWorkOutLog")
    private final List<WorkOutLog> singleLogs = new ArrayList<WorkOutLog>();

    @JsonFormat(pattern="yyyy-MM-dd")
    private final Date date = new Date(System.currentTimeMillis());
    private final String logName;

    private final Double totalVolume;

    public DailyWorkOutLog() {
        this.logName = null;
        this.totalVolume = null;
        this.Id = null;
        this.user = null;
    }

    public void setSingleLogs(List<WorkOutLog> logs){
        this.singleLogs.addAll(logs);
    }
}
