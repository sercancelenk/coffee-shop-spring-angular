package sr.api.core.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sr.api.core.business.service.IShopOrderDetailService;
import sr.api.core.persistence.dao.IShopOrderDetailDAO;
import sr.api.core.persistence.domain.ShopOrderDetail;

/**
 * Created by byzas on 26/01/16.
 */

@Service(value = "shopOrderDetailService")
public class ShopOrderDetailServiceImpl implements IShopOrderDetailService{

    @Autowired
    IShopOrderDetailDAO shopOrderDetailDAO;

    @Autowired LoggerServiceImpl loggerService;

    @Override
    public void saveShopOrderDetail(ShopOrderDetail shopOrderDetail) {
        try {
            shopOrderDetailDAO.saveShopOrderDetail(shopOrderDetail);
        } catch (Exception ex) {
            loggerService.error(ex.getMessage(), Thread.currentThread().getStackTrace()[1]);
        }

    }
}
