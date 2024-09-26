package com.ubi.dbp.proxy.service.model;

import lombok.Data;

@Data
public class MbRegistrationResp {

    private boolean first_time_registration;
    private boolean device_changed;
    private boolean sim_changed;
    private boolean mobile_number_changed;
    private boolean loginpinSet;
    private boolean tpinSet;
    private boolean mfaSuccess;
    private boolean questionsSet;
}
 