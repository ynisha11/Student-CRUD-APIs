package com.example.sample.studentProject.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.sample.studentProject.bean.Student;

@Repository
public class StudentDaoImp implements StudentDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Student createStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.save(student);
		return student;
	}

	@Override
	public List<Student> getAllStudents() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Student WHERE isActive = true");
		return query.list();
	}

	@Override
	public Student findByRollNo(int rollNo) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Student WHERE isActive = true AND rollNo = :rollNo");
		query.setParameter("rollNo", rollNo);
		return (Student) query.uniqueResult();
	}

	@Override
	public Student updateStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("from Student where isActive = true && rollNo = :rollNo");
//		query.setParameter(rollNo, rollNo);
//		return (Student) query.uniqueResult();
		session.update(student);
		return student;
	}

}