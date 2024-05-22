package org.example.demo_insta_app.demo;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemoRepository extends JpaRepository<DemoUser, Integer> {
    @Query("""
            select u.age,u.firstname,u.lastname from DemoUser as u
            """)
    List<Tuple> getSomeFields();


    /* @Query("""
             select u.age,count(u),max(u.age),min(u.age) from DemoUser as u group by u.age
             """)*/
    @Query(value = "select age, count(*), max(age), min(age)\n" +
            "from demo_user\n" +
            "group by age\n" +
            "limit 3", nativeQuery = true)
    List<Tuple> groupByWithUserAge();

    @Query(value = "select u.age, d.password, d.username\n" +
            "from demo_user as d\n" +
            "         left join user_to_pdf as u on d.id = u.age where u.age is not null ", nativeQuery = true)
    List<Tuple> addedUserAndDemoSomeFields();
}
