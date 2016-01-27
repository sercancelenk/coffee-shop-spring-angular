package sr.api.core.persistence.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sr.api.core.persistence.dao.IShopOrderDAO;
import sr.api.core.persistence.domain.ShopOrder;

import java.util.List;

/**
 * Created by byzas on 26/01/16.
 */

@Repository(value = "shopOrderDAO")
@Transactional
public class ShopOrderDAOImpl extends AbstractHibernateDAO<ShopOrder, Long> implements IShopOrderDAO {
    @Override
    public void saveShopOrder(List<ShopOrder> shopOrder) {
        for (ShopOrder so : shopOrder)
            save(so);
    }

    @Override
    public List getTotalPricesByCustomer() {
        List list = null;
        try {
            list = (List) hibernateTemplate.execute(new HibernateCallback<List>() {
                                                        public List doInHibernate(Session s) throws HibernateException {
                                                            List result = s.createCriteria(persistentClass)
                                                                    .setProjection(Projections.projectionList()
                                                                                    .add(Projections.groupProperty("customer"))
                                                                                    .add(Projections.sum("total_price"))
                                                                    ).list();

                                                            return result;

                                                        }
                                                    }
            );
        } catch (Exception ex) {

        }
        return list;
    }

    @Override
    public List getTotalPricesByProductBase() {
        List list = null;
        try {
            list = (List)hibernateTemplate.execute(new HibernateCallback<List>()
                                                   {
                                                       public List doInHibernate(Session s) throws HibernateException
                                                       {
                                                           SQLQuery sql=s.createSQLQuery("select a.name, sum(a.total_price) from " +
                                                                   "(select p.id, p.name, sum(sod.price) as total_price " +
                                                                   "from shop_order so, shop_order_detail sod, products p " +
                                                                   "where so.id = sod.shopOrder_id and sod.product_id = p.id " +
                                                                   "group by sod.item_id " +
                                                                   "order by p.id) a " +
                                                                   "group by a.name");
                                                           return sql.list();
                                                       }
                                                   }
            );
        } catch (Exception ex) {
            list=null;
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public List getTotalPricesByProductExtra() {
        List list = null;
        try {
            list = (List)hibernateTemplate.execute(new HibernateCallback<List>()
                                                   {
                                                       public List doInHibernate(Session s) throws HibernateException
                                                       {
                                                           SQLQuery sql=s.createSQLQuery("select p.id, p.name, sum(sod.price) " +
                                                                   "from shop_order so, shop_order_detail sod, products p " +
                                                                   "where so.id = sod.shopOrder_id and sod.product_id = p.id " +
                                                                   "and p.product_type = 'EXTRA' " +
                                                                   "group by p.id, p.name " +
                                                                   "order by p.id");
                                                           return sql.list();
                                                       }
                                                   }
            );
        } catch (Exception ex) {
            list=null;
            ex.printStackTrace();
        }
        return list;
    }


}
