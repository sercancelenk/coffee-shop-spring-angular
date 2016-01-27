package sr.api.core.business.service;

import java.util.List;

/**
 * Created by byzas on 27/01/16.
 */
public interface IReportService {
    List getTotalPricesByCustomer();
    List getTotalPricesByProductBase();
    List getTotalPricesByProductExtra();
}
