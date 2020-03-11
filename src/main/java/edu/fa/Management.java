package edu.fa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import edu.fa.model.Address;
import edu.fa.model.Course;
import edu.fa.model.Fresher;
import edu.fa.model.Group;
import edu.fa.model.syllabus;

public class Management {

	public static void main(String[] args) {
		/*
		 * CreateCourseSyllabuses(); getCourseSyllabuses(1);
		 */
		/*
		 * CreateFresherandCourse();
		 */
		/* CreateFresherandGroup(); */
		createGroup();
		showfirstLevel();
		ConnectionUtil.getSessionFactory().close();
	}
	private static void showfirstLevel() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group group = (Group) session.get(Group.class,1);
			System.out.println(group);
			session.getTransaction().commit();
			session.close();
			
			session = sessionFactory.openSession();
			session.beginTransaction();
			group=null;
			group = (Group) session.get(Group.class,1);
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void useNamedQuery() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.getNamedQuery("groupByName");
			query.setParameter("name", "javaScript Group");
			System.out.println(query.list());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void useCriteria() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Criteria groupCriteria = session.createCriteria(Group.class);
			SimpleExpression eq = Restrictions.eq("id", 1);
			SimpleExpression like = Restrictions.like("name", "javaS%");
			LogicalExpression or = Restrictions.or(eq, like);
			groupCriteria.add(or);
			System.out.println(groupCriteria.list());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void UpdateGroupusingHQL() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "update Group set name = :name where id =:id  ";
			Query query = session.createQuery(queryStr);
			query.setParameter("id", 1);
			query.setParameter("name", "Update Data Groups");
			int result = query.executeUpdate();
			System.out.println(result);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

	private static void queryGroupusingHQL() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String queryStr = "SELECT name FROM Group where id = :id and name like :name  ";
			Query query = session.createQuery(queryStr);
			query.setParameter("id", 1);
			query.setParameter("name", "java%");
			List<Group> groups = (List<Group>) query.list();
			System.out.println(groups);

		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

	private static void getGroup() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group jsGroup = (Group) session.get(Group.class, 2);
			System.out.println(jsGroup);
			jsGroup.setName("javascript after update");
			session.delete(jsGroup);
			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

	private static void createGroup() {

		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Group javaGroup = new Group("java Group");
			Group jsGroup = new Group("javaScript Group");
			session.save(javaGroup);
			session.save(jsGroup);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

	private static void CreateFresherandGroup() {
		Fresher fresher1 = new Fresher();
		Fresher fresher2 = new Fresher();
		Group group1 = new Group("group1");
		Group group2 = new Group("group2");
		Set<Fresher> fresher = new HashSet<Fresher>();
		fresher.add(fresher1);
		fresher.add(fresher2);
		Set<Group> groups = new HashSet<Group>();
		groups.add(group1);
		groups.add(group2);
		fresher1.setName("fresher1");
		fresher2.setName("fresher2");
		fresher1.setGroups(groups);
		fresher2.setGroups(groups);
		group1.setFreshers(fresher);
		group2.setFreshers(fresher);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(fresher1);
			session.save(fresher2);
			session.save(group1);
			session.save(group2);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

	private static void CreateFresherandCourse() {
		List<Course> courses = new ArrayList<>();
		courses.add(new Course("java"));
		courses.add(new Course("hibernate"));
		Fresher fresher = new Fresher("nguyentiendong", courses);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			for (Course course : courses) {
				session.save(course);
			}
			session.save(fresher);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

	private static void CreateFresherandAddress() {
		Address address = new Address("my duc", "ha noi");
		Fresher fresher = new Fresher("nguyentiendong", address);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(address);
			session.save(fresher);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

	private static void getCourseSyllabuses(int id) {
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Course course = (Course) session.get(Course.class, id);
			System.out.println(course.getName());
			session.getTransaction().commit();
			session.close();
			System.out.println(course.getSyllabusList());
		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

	private static void CreateCourseSyllabuses() {
		List<syllabus> syllabusList = new ArrayList<syllabus>();
		syllabusList.add(new syllabus("hibernate Online content", 30));
		syllabusList.add(new syllabus("hibernate Offline content", 50));
		Course course = new Course("nguyen tien dong", new Date(), syllabusList);
		SessionFactory sessionFactory = ConnectionUtil.getSessionFactory();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(course);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());

		}
	}

}
