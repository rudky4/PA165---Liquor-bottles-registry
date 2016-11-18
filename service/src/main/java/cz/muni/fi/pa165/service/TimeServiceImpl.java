package cz.muni.fi.pa165.service;

import java.util.Date;

/**
 * @author mhajas
 */
public class TimeServiceImpl implements TimeService {

    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}
