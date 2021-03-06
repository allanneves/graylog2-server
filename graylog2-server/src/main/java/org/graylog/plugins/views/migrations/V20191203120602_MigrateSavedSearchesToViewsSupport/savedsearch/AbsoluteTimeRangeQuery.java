/*
 * Copyright (C) 2020 Graylog, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Server Side Public License, version 1,
 * as published by MongoDB, Inc.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * Server Side Public License for more details.
 *
 * You should have received a copy of the Server Side Public License
 * along with this program. If not, see
 * <http://www.mongodb.com/licensing/server-side-public-license>.
 */
package org.graylog.plugins.views.migrations.V20191203120602_MigrateSavedSearchesToViewsSupport.savedsearch;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;
import org.graylog.plugins.views.migrations.V20191203120602_MigrateSavedSearchesToViewsSupport.view.AbsoluteRange;
import org.graylog.plugins.views.migrations.V20191203120602_MigrateSavedSearchesToViewsSupport.view.TimeRange;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import java.util.Optional;

@AutoValue
@JsonAutoDetect
public abstract class AbsoluteTimeRangeQuery extends Query {
    public static final String type = "absolute";

    public abstract DateTime from();
    public abstract DateTime to();

    @Override
    public TimeRange toTimeRange() {
        return AbsoluteRange.create(from(), to());
    }

    @JsonCreator
    static AbsoluteTimeRangeQuery create(
            @JsonProperty("rangeType") String rangeType,
            @JsonProperty("fields") @Nullable String fields,
            @JsonProperty("query") String query,
            @JsonProperty("from") DateTime from,
            @JsonProperty("to") DateTime to,
            @JsonProperty("streamId") @Nullable String streamId,
            @JsonProperty("interval") @Nullable String ignored
    ) {
        return new AutoValue_AbsoluteTimeRangeQuery(rangeType, Optional.ofNullable(fields), query, Optional.ofNullable(streamId), from, to);
    }
}
