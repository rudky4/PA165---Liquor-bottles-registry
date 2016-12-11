package cz.muni.fi.pa165.service;

import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author mhajas
 */
@Service
public class TimeServiceImpl implements TimeService {

    @Override
    public Date getCurrentDate() {
        return new Date();
    }
}
