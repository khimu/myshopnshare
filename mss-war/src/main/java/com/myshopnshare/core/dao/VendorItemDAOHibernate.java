package com.myshopnshare.core.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.myshopnshare.core.domain.Person;
import com.myshopnshare.core.domain.VendorItem;
import com.myshopnshare.core.enums.CategoryType;
@Repository("vendorItemDAO")
public class VendorItemDAOHibernate extends
		GenericDAOHibernate<VendorItem, Long> implements VendorItemDAO {
	
	public VendorItem findSleekSwapItem(String itemName){
		String hql = "select distinct e FROM VendorItem e left join e.person.emails s WHERE s.email = 'sleekswap@gmail.com' and upper(e.itemName) = :itemName";
		Query q = getSession().createQuery(hql);
		q.setParameter("itemName", itemName.toUpperCase().trim());
		q.setCacheable(true);
		return (VendorItem) q.uniqueResult();		
	}
	
	public VendorItem getVendorItemForPerson(Person person, Long id){
		String hql = "select e FROM VendorItem e WHERE e.person = :person and e.id = :id";
		Query q = getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setParameter("id", id);
		q.setCacheable(true);
		return (VendorItem) q.uniqueResult();		
	}
	public VendorItem findBasicPackage() {
		String hql = "FROM VendorItem WHERE serialNumber = '8811881'";
		Query q = getSession().createQuery(hql);
		// If the user is in personVisibilityDomaiin, it is a custom group and
		// the user already has access to the item just by being in the
		// visibility domain list.
		q.setCacheable(true);
		return (VendorItem) q.uniqueResult();
	}

	public VendorItem findPremiumPackage() {
		String hql = "FROM VendorItem WHERE serialNumber = '8811882'";
		Query q = getSession().createQuery(hql);
		// If the user is in personVisibilityDomaiin, it is a custom group and
		// the user already has access to the item just by being in the
		// visibility domain list.
		q.setCacheable(true);
		return (VendorItem) q.uniqueResult();
	}

	public VendorItem findCorporatePackage() {
		String hql = "FROM VendorItem WHERE serialNumber = '8811883'";
		Query q = getSession().createQuery(hql);
		q.setCacheable(true);
		return (VendorItem) q.uniqueResult();
	}
	
	public List<VendorItem> findHotestItems(int start) {

		StringBuilder hql = new StringBuilder(
				"select distinct m FROM VendorItem m "
						+ "where m in (select ic.item from ItemCategory ic "
						+ "WHERE ic.category = :recommend or ic.category = :bought OR ic.category = :want ");
		hql.append("group by ic.item ");
		hql.append("having count(ic.item) > 0 ");
		hql.append("ORDER BY count(ic.item) DESC) ");
		hql.append("AND m.active = true ");
		hql.append("order by m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("want", CategoryType.WANT);
		q.setParameter("bought", CategoryType.BOUGHT);
		q.setParameter("recommend", CategoryType.RECOMMEND);
		q.setMaxResults(36);
		q.setFetchSize(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findHotestItems(CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m from VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category "
						+ "and m in (select ic.item from ItemCategory ic "
						+ "where ic.category = :recommend or ic.category = :bought OR ic.category = :want "
						+ "group by ic.item " + "having count(ic.item) > 0 "
						+ "order by count(ic.item) DESC) "
						+ "and m.active = true "
						+ "order by m, m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("want", CategoryType.WANT);
		q.setParameter("bought", CategoryType.BOUGHT);
		q.setParameter("recommend", CategoryType.RECOMMEND);
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFetchSize(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findHotestItems(List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m from VendorItem m "
						+ "left join m.tags t where ");
		int index = appendTag(tags, hql);
		hql
				.append(" and m in (select ic.item from ItemCategory ic "
						+ "where ic.category = :recommend or ic.category = :bought OR ic.category = :want "
						+ "group by ic.item " + "having count(ic.item) > 0 "
						+ "ORDER BY count(ic.item) DESC) "
						+ "and m.active = true "
						+ "order by m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("want", CategoryType.WANT);
		q.setParameter("bought", CategoryType.BOUGHT);
		q.setParameter("recommend", CategoryType.RECOMMEND);
		q.setCacheable(true);
		q.setMaxResults(36);
		q.setFirstResult(start);
		return q.list();
	}

	public List<VendorItem> findHotestItems(List<String> tags,
			CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m from VendorItem m "
						+ "left join m.tags t where ");
		int index = appendTag(tags, hql);
		hql.append(" and m in (select ic.item from ItemCategory ic "
				+ "where ic.category = :category " + "group by ic.item "
				+ "having count(ic.item) > 0 "
				+ "ORDER BY count(ic.item) DESC) " + "and m.active = true "
				+ "order by m.enteredDate desc");

		hql.append(" ) AND m.active = true ");
		hql.append("order by m.enteredDate desc");
		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}

		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findCheapest(int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m FROM VendorItem m "
						+ "where m.price in (select i.price "
						+ "from VendorItem i " + "group by i.price "
						+ "having i.price > 0 " + "order by i.price asc) ");
		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findCheapest(CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category and ");
		hql.append("m.price in (select i.price from VendorItem i "
				+ "group by i.price " + "having i.price > 0 "
				+ "order by i.price asc) ");
		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findCheapest(List<String> tags,
			CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "left join m.tags t "
						+ "where ics.category = :category and ");
		int index = appendTag(tags, hql);
		hql.append(" and m.price in (select i.price from VendorItem i "
				+ "group by i.price " + "having i.price > 0 "
				+ "order by i.price asc) ");
		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findCheapest(List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m "
						+ "left join m.tags t where ");
		int index = appendTag(tags, hql);
		hql.append(" and m.price in " + "(select i.price from VendorItem i "
				+ "group by i.price " + "having i.price > 0 "
				+ "order by i.price asc) ");
		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}

		q.setCacheable(true);
		q.setMaxResults(36);
		q.setFirstResult(start);
		return q.list();
	}

	/** THE BIGGER THE CLEARANCE THE BETTER **/
	public List<VendorItem> findClearance(int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m "
						+ "WHERE m.clearancePercentage in ("
						+ "select i.clearancePercentage "
						+ "from VendorItem i "
						+ "group by i.clearancePercentage "
						+ "having i.clearancePercentage > 0 "
						+ "order by i.clearancePercentage asc) ");

		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	/** THE BIGGER THE CLEARANCE THE BETTER **/
	public List<VendorItem> findClearance(CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category "
						+ "and m.clearancePercentage in ("
						+ "select i.clearancePercentage "
						+ "from VendorItem i "
						+ "group by i.clearancePercentage "
						+ "having i.clearancePercentage > 0 "
						+ "order by i.clearancePercentage asc) ");

		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findClearance(List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m " + "left join m.tags t "
						+ "where ");
		int index = appendTag(tags, hql);
		hql.append(" and m.clearancePercentage in ("
				+ "select i.clearancePercentage " + "from VendorItem i "
				+ "group by i.clearancePercentage "
				+ "having i.clearancePercentage > 0 "
				+ "order by i.clearancePercentage desc) ");

		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findClearance(List<String> tags,
			CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"select distinct m from VendorItem m "
						+ "left join m.itemCategories ics "
						+ "left join m.tags t "
						+ "where ics.category = :category and ");
		int index = appendTag(tags, hql);
		hql.append(" and m.clearancePercentage in ("
				+ "select i.clearancePercentage " + "from VendorItem i "
				+ "group by i.clearancePercentage "
				+ "having i.clearancePercentage > 0 "
				+ "order by i.clearancePercentage desc) ");
		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	/*
	 * select from vendor_item i left join item ii on i.id = ii.id where
	 * i.rebateAmount in (select m.rebateAmount from vendor_item m group by
	 * m.rebateAmount having m.rebateAmount > 0 order by max(m.rebateAmount)
	 * desc) and ii.active = false order by ii.entereddate desc (non-Javadoc)
	 * 
	 * @see com.myshopnshare.core.dao.VendorItemDAO#findRebates()
	 */

	public List<VendorItem> findRebates(int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m "
						+ "WHERE m.rebateAmount in ("
						+ "select i.rebateAmount " + "from VendorItem i "
						+ "group by i.rebateAmount "
						+ "having i.rebateAmount > 0 "
						+ "order by max(i.rebateAmount) desc) ");
		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setCacheable(true);
		q.setMaxResults(36);
		q.setFirstResult(start);
		return q.list();
	}

	public List<VendorItem> findRebates(CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category "
						+ "and m.rebateAmount in (" + "select i.rebateAmount "
						+ "from VendorItem i " + "group by i.rebateAmount "
						+ "having i.rebateAmount > 0 "
						+ "order by max(i.rebateAmount) desc) ");
		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findRebates(List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m " + "left join m.tags t "
						+ "where ");
		int index = appendTag(tags, hql);

		hql.append(" and m.rebateAmount in (" + "select i.rebateAmount "
				+ "from VendorItem i " + "group by i.rebateAmount "
				+ "having i.rebateAmount > 0 "
				+ "order by i.rebateAmount desc) ");
		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findRebates(List<String> tags, CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m " + "left join m.tags t "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category and ");
		int index = appendTag(tags, hql);
		hql.append(" and m.rebateAmount in (" + "select i.rebateAmount "
				+ "from VendorItem i " + "group by i.rebateAmount "
				+ "having i.rebateAmount > 0 "
				+ "order by i.rebateAmount desc) ");
		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findFree(int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m WHERE ");
		hql.append("m.price = 0 and m.active = true ");
		hql.append("order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findFree(CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category "
						+ "and m.price = 0 " + "and m.active = true "
						+ "order by m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setCacheable(true);
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		return q.list();
	}

	public List<VendorItem> findFree(List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m " + "left join m.tags t "
						+ "where ");
		int index = appendTag(tags, hql);
		hql.append(" and m.price = 0 and m.active = true ");
		hql.append("order by m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findFree(List<String> tags, CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m " + "left join m.tags t "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category and ");
		int index = appendTag(tags, hql);
		hql.append(" and m.price = 0 " + "and m.active = true "
				+ "order by m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findAllVendorItemsFor(Person person, int start) {
		String hql = "from VendorItem v where v.person = :person AND v.active = true";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	/** WORLD ITEMS SEARCH AGAINST NEW ITEMS **/
	public List<VendorItem> findWorldVendorItems(List<String> tags, int start) {
		StringBuilder hql = new StringBuilder(
		// "select new com.myshopnshare.core.domain.VendorItem(m.id, ) FROM Item m left join m.tags t WHERE m.id = t.item.id and m.type = 'MERCHANT' or m.type ='ADS' or m.type = 'INSTITUTION' or m.type='BUSINESS_SERVICE' and ");

				"select distinct m " 
				+ "FROM VendorItem m "
						+ "left join m.tags t "
						+ "where ");
		int index = appendTag(tags, hql);
		hql.append(" AND m.active = true");
		hql.append(" ORDER BY m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	/** WORLD ITEMS SEARCH NEW ITEMS **/
	public List<VendorItem> findWorldVendorItems(int start) {
		StringBuilder hql = new StringBuilder("FROM VendorItem m  WHERE ");
		hql.append(" m.active = true");
		hql.append(" ORDER BY m.enteredDate DESC");

		Query q = getSession().createQuery(hql.toString());
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findWorldVendorItems(CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category ");
		hql.append("and m.active = true ");
		hql.append("order by m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}

	public List<VendorItem> findWorldVendorItems(List<String> tags,
			CategoryType category, int start) {
		StringBuilder hql = new StringBuilder(
				"SELECT distinct m FROM VendorItem m " + "left join m.tags t "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category and ");
		int index = appendTag(tags, hql);

		hql.append(" and m.active = true ");
		hql.append("order by m.enteredDate desc");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("category", category);
		q.setMaxResults(36);
		q.setFirstResult(start);
		q.setCacheable(true);
		return q.list();
	}
	
	public Long findHotestItemsCount() {
		StringBuilder hql = new StringBuilder(
				"select count(m) FROM VendorItem m "
						+ "where m in (select ic.item from ItemCategory ic "
						+ "WHERE ic.category = :recommend or ic.category = :bought OR ic.category = :want ");
		hql.append("group by ic.item ");
		hql.append("having count(ic.item) > 0 ");
		hql.append("ORDER BY count(ic.item) DESC) ");
		hql.append("AND m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("want", CategoryType.WANT);
		q.setParameter("bought", CategoryType.BOUGHT);
		q.setParameter("recommend", CategoryType.RECOMMEND);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findHotestItemsCount(CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select count (m) from VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category "
						+ "and m in (select ic.item from ItemCategory ic "
						+ "where ic.category = :recommend or ic.category = :bought OR ic.category = :want "
						+ "group by ic.item " + "having count(ic.item) > 0 "
						+ "order by count(ic.item) DESC) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("want", CategoryType.WANT);
		q.setParameter("bought", CategoryType.BOUGHT);
		q.setParameter("recommend", CategoryType.RECOMMEND);
		q.setParameter("category", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findHotestItemsCount(List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"select  count (m) from VendorItem m "
						+ "left join m.tags t where ");
		int index = appendTag(tags, hql);
		hql
				.append(" and m in (select ic.item from ItemCategory ic "
						+ "where ic.category = :recommend or ic.category = :bought OR ic.category = :want "
						+ "group by ic.item " + "having count(ic.item) > 0 "
						+ "ORDER BY count(ic.item) DESC) "
						+ "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("want", CategoryType.WANT);
		q.setParameter("bought", CategoryType.BOUGHT);
		q.setParameter("recommend", CategoryType.RECOMMEND);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findHotestItemsCount(List<String> tags,
			CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select count (m) from VendorItem m "
						+ "left join m.tags t where ");
		int index = appendTag(tags, hql);
		hql.append(" and m in (select ic.item from ItemCategory ic "
				+ "where ic.category = :category " + "group by ic.item "
				+ "having count(ic.item) > 0 "
				+ "ORDER BY count(ic.item) DESC) " + "and m.active = true ");
		
		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}

		q.setParameter("category", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findCheapestCount() {
		StringBuilder hql = new StringBuilder(
				"select  count (m) FROM VendorItem m "
						+ "where m.price in (select i.price "
						+ "from VendorItem i " + "group by i.price "
						+ "having i.price > 0 " + "order by i.price asc) ");
		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findCheapestCount(CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category and ");
		hql.append("m.price in (select i.price from VendorItem i "
				+ "group by i.price " + "having i.price > 0 "
				+ "order by i.price asc) ");
		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("category", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findCheapestCount(List<String> tags,
			CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "left join m.tags t "
						+ "where ics.category = :category and ");
		int index = appendTag(tags, hql);
		hql.append(" and m.price in (select i.price from VendorItem i "
				+ "group by i.price " + "having i.price > 0 "
				+ "order by i.price asc) ");
		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("category", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findCheapestCount(List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m "
						+ "left join m.tags t where ");
		int index = appendTag(tags, hql);
		hql.append(" and m.price in " + "(select i.price from VendorItem i "
				+ "group by i.price " + "having i.price > 0 "
				+ "order by i.price asc) ");
		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}

		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** THE BIGGER THE CLEARANCE THE BETTER **/
	public Long findClearanceCount() {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m "
						+ "WHERE m.clearancePercentage in ("
						+ "select i.clearancePercentage "
						+ "from VendorItem i "
						+ "group by i.clearancePercentage "
						+ "having i.clearancePercentage > 0 "
						+ "order by i.clearancePercentage asc) ");

		hql.append("and m.active = true ");
		Query q = getSession().createQuery(hql.toString());
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** THE BIGGER THE CLEARANCE THE BETTER **/
	public Long findClearanceCount(CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category "
						+ "and m.clearancePercentage in ("
						+ "select i.clearancePercentage "
						+ "from VendorItem i "
						+ "group by i.clearancePercentage "
						+ "having i.clearancePercentage > 0 "
						+ "order by i.clearancePercentage asc) ");

		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("category", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findClearanceCount(List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m " + "left join m.tags t "
						+ "where ");
		int index = appendTag(tags, hql);
		hql.append(" and m.clearancePercentage in ("
				+ "select i.clearancePercentage " + "from VendorItem i "
				+ "group by i.clearancePercentage "
				+ "having i.clearancePercentage > 0 "
				+ "order by i.clearancePercentage desc) ");

		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}

		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findClearanceCount(List<String> tags,
			CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"select  count (m) from VendorItem m "
						+ "left join m.itemCategories ics "
						+ "left join m.tags t "
						+ "where ics.category = :category and ");
		int index = appendTag(tags, hql);
		hql.append(" and m.clearancePercentage in ("
				+ "select i.clearancePercentage " + "from VendorItem i "
				+ "group by i.clearancePercentage "
				+ "having i.clearancePercentage > 0 "
				+ "order by i.clearancePercentage desc) ");
		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("category", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findRebatesCount() {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m "
						+ "WHERE m.rebateAmount in ("
						+ "select i.rebateAmount " + "from VendorItem i "
						+ "group by i.rebateAmount "
						+ "having i.rebateAmount > 0 "
						+ "order by max(i.rebateAmount) desc) ");
		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findRebatesCount(CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category "
						+ "and m.rebateAmount in (" + "select i.rebateAmount "
						+ "from VendorItem i " + "group by i.rebateAmount "
						+ "having i.rebateAmount > 0 "
						+ "order by max(i.rebateAmount) desc) ");
		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("category", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findRebatesCount(List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m " + "left join m.tags t "
						+ "where ");
		int index = appendTag(tags, hql);

		hql.append(" and m.rebateAmount in (" + "select i.rebateAmount "
				+ "from VendorItem i " + "group by i.rebateAmount "
				+ "having i.rebateAmount > 0 "
				+ "order by i.rebateAmount desc) ");
		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}

		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findRebatesCount(List<String> tags, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m " + "left join m.tags t "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category and ");
		int index = appendTag(tags, hql);
		hql.append(" and m.rebateAmount in (" + "select i.rebateAmount "
				+ "from VendorItem i " + "group by i.rebateAmount "
				+ "having i.rebateAmount > 0 "
				+ "order by i.rebateAmount desc) ");
		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("category", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findFreeCount() {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m WHERE ");
		hql.append("m.price = 0 and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findFreeCount(CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category "
						+ "and m.price = 0 " + "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setCacheable(true);
		q.setParameter("category", category);
		return (Long)q.uniqueResult();
	}

	public Long findFreeCount(List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m " + "left join m.tags t "
						+ "where ");
		int index = appendTag(tags, hql);
		hql.append(" and m.price = 0 and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}

		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findFreeCount(List<String> tags, CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT  count (m) FROM VendorItem m " + "left join m.tags t "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category and ");
		int index = appendTag(tags, hql);
		hql.append(" and m.price = 0 " + "and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("category", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findAllVendorItemsForCount(Person person) {
		String hql = "select count(v) from VendorItem v where v.person = :person AND v.active = true";
		Query q = this.getSession().createQuery(hql);
		q.setParameter("person", person);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** WORLD ITEMS SEARCH AGAINST NEW ITEMS **/
	public Long findWorldVendorItemsCount(List<String> tags) {
		StringBuilder hql = new StringBuilder(
				"select count(m) " 
				+ "FROM VendorItem m "
						+ "left join m.tags t "
						+ "where ");
		int index = appendTag(tags, hql);
		hql.append(" AND m.active = true");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}

		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	/** WORLD ITEMS SEARCH NEW ITEMS **/
	public Long findWorldVendorItemsCount() {
		StringBuilder hql = new StringBuilder("select count(m) FROM VendorItem m  WHERE ");
		hql.append(" m.active = true");

		Query q = getSession().createQuery(hql.toString());
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findWorldVendorItemsCount(CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT count(m) FROM VendorItem m "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category ");
		hql.append("and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		q.setParameter("category", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

	public Long findWorldVendorItemsCount(List<String> tags,
			CategoryType category) {
		StringBuilder hql = new StringBuilder(
				"SELECT count(m) FROM VendorItem m " + "left join m.tags t "
						+ "left join m.itemCategories ics "
						+ "where ics.category = :category and ");
		int index = appendTag(tags, hql);

		hql.append(" and m.active = true ");

		Query q = getSession().createQuery(hql.toString());
		for (int i = 0; i < index; i++) {
			q.setParameter("tag" + i, tags.get(i));
		}
		q.setParameter("category", category);
		q.setCacheable(true);
		return (Long)q.uniqueResult();
	}

}
