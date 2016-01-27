package sr.api.core.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sr.api.core.business.service.IReportService;
import sr.api.core.persistence.dao.IShopOrderDAO;

import java.util.List;

/**
 * Created by byzas on 27/01/16.
 */

@Service(value = "reportService")
public class ReportServiceImpl implements IReportService {

    @Autowired
    LoggerServiceImpl loggerService;

    @Autowired
    IShopOrderDAO shopOrderDAO;

    @Override
    public List getTotalPricesByCustomer() {
        try {
            return shopOrderDAO.getTotalPricesByCustomer();
        } catch (Exception ex) {
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
        return null;
    }

    @Override
    public List getTotalPricesByProductBase() {
        try {
            return shopOrderDAO.getTotalPricesByProductBase();
        } catch (Exception ex) {
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
        return null;
    }

    @Override
    public List getTotalPricesByProductExtra() {
        try {
            return shopOrderDAO.getTotalPricesByProductExtra();
        } catch (Exception ex) {
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }
        return null;
    }
}
