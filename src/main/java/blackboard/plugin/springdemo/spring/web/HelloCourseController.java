package blackboard.plugin.springdemo.spring.web;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.user.User;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.persist.user.UserDbLoader;
import blackboard.platform.spring.web.annotations.IdParam;

@Controller
public class HelloCourseController
{

  // Annotates a variable so that the Spring container will 
  // attempt to wire the reference for you automatically.
  @Autowired
  private CourseMembershipDbLoader _membershipLoader;

  @Autowired
  private UserDbLoader _userLoader;

  @RequestMapping( "/course_users" )
  // @IdParam will take the string in the request parameter listed and use it as an Id object
  // to look up the object based on the type.   In this case, it will convert the string to 
  // an Id and look up the Course based on the Id.
  public ModelAndView listCourseUsers( @IdParam( "cid" ) Course course )
    throws KeyNotFoundException, PersistenceException
  {
    ModelAndView mv = new ModelAndView( "course_users" );
    mv.addObject( "course", course );

    // Load some data and put it in the model
    List<CourseMembership> members = CourseMembershipDbLoader.Default.getInstance().loadByCourseId( course.getId() );
    List<User> users = new LinkedList<User>();
    for ( CourseMembership member : members )
    {
      users.add( _userLoader.loadById( member.getUserId() ) );
    }
    mv.addObject( "users", users );

    if (users.size() > 0){ // demonstrate the use of loadByCourseAndUserId
      CourseMembership member2 = CourseMembershipDbLoader.Default.getInstance().loadByCourseAndUserId( course.getId(), users.get(0).getId() );
      User user2 = null;
  
        user2 = _userLoader.loadById( member2.getUserId() );

      mv.addObject("user2", user2);
    }// end demonstrate the use of loadByCourseAndUserId
    return mv;
  }

}
