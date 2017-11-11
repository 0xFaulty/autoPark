package com.defaulty.autopark.service.report;

public enum ReportType {
    CAR_OWNERS,
    TOP_TIME;

    public String getDescription() {
        switch (this) {
            case CAR_OWNERS:
                return "'Car owners report'";
            case TOP_TIME:
                return "'Records for each routes'";
            default:
                return "'Unknown report'";
        }
    }

}
