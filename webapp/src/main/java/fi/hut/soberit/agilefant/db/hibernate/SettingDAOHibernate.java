package fi.hut.soberit.agilefant.db.hibernate;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fi.hut.soberit.agilefant.db.SettingDAO;
import fi.hut.soberit.agilefant.model.Setting;

@Repository("settingDAO")
public class SettingDAOHibernate extends GenericDAOHibernate<Setting> implements SettingDAO {
       
        public SettingDAOHibernate() {
            super(Setting.class);
        }

        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("unchecked")
        public Setting getByName(String name) {
        	Criteria criteria = this.createCriteria(this.getPersistentClass());
        	criteria.add(Restrictions.eq("name", name));
        	return super.getFirst(criteria.list());
        }
       
        /**
         * {@inheritDoc}
         */
        @SuppressWarnings("unchecked")
        public List<Setting> getAllOrderByName(){
        	Criteria criteria = this.createCriteria(this.getPersistentClass());
        	criteria.addOrder(Order.asc("name"));
        	return criteria.list();
        }

		@Override
		public Collection<Setting> getAll() {
			return this.hibernateTemplate.loadAll(getPersistentClass());
		}
}
