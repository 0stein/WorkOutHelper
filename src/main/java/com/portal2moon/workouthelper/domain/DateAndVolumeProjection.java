package com.portal2moon.workouthelper.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public interface DateAndVolumeProjection {
    @JsonFormat(pattern="yyyy-MM-dd")
    Date getDate();
    Double getVolume();
}
