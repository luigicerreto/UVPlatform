package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import controller.ServletCommon;
import controller.ServletSignup;
import controller.ServletStudent;
import interfacce.UserInterface;

import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import model.Request;
import model.Student;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class ServletSignupTest extends Mockito {
  private ServletSignup servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  /**
   * Before.
   */
  @Before
  public void setUp() {
    servlet = new ServletSignup();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  
  request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
  }
	  @Test
	  public void addNewStudentFailNameNumber() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe1");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "g.c@unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  } 
	  
	  @Test
	  public void addNewStudentFailNameNull() throws ServletException, IOException  {
	    request.addParameter("name", "");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "g.c@unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  } 
	  
	  @Test
	  public void addNewStudentFailNameOver() throws ServletException, IOException  {
	    request.addParameter("name", "aaaaaaaaaaaaaaaaaaaaa");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "g.c@unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudentFailFlag() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "g.cir@unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "");
	    assertThrows(IllegalArgumentException.class, () -> {
		      servlet.doPost(request, response);
		    });
	  }
	  
	  @Test
	  public void addNewStudentFailSurnameNull() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "");
	    request.addParameter("email", "g.c@unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudentFailSurnameNumber() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "C1rino");
	    request.addParameter("email", "g.c@unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudentFailSurnameOver() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "aaaaaaaaaaaaaaaaaaaaa");
	    request.addParameter("email", "g.c@unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudentFailEmail() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "@unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudentFailEmail2() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "@studenti.unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudentFailEmail3() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "aaaa@studenti.unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudentFailEmail4() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "a.@studenti.unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudentFailEmailEmpty() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudentFailSex() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "g.c@studenti.unisa.it");
	    request.addParameter("sex", "L");
	    request.addParameter("password", "pass");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudentSexF() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "g.c@studenti.unisa.it");
	    request.addParameter("sex", "F");
	    request.addParameter("password", "pass");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudentSexM() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "g.c@studenti.unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "pass");
	    request.addParameter("flag", "1");
	    assertThrows(IllegalArgumentException.class, () -> {
	      servlet.doPost(request, response);
	    });
	  }
	  
	  @Test
	  public void addNewStudent() throws ServletException, IOException  {
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", "g.c@studenti.unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    servlet.doPost(request, response);
	    assertEquals("json", response.getContentType());
	  }
	  
	  @Test
	  public void addNewStudent1() throws ServletException, IOException  {
	    String Ab = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    SecureRandom rnd = new SecureRandom();
	    StringBuilder sb = new StringBuilder(10);
	    // Crea una stringa random per l'email di lunghezza 10
	    for (int i = 0; i < 10; i++) {
	      sb.append(Ab.charAt(rnd.nextInt(Ab.length()))); 
	    }
	    request.addParameter("name", "Giuseppe");
	    request.addParameter("surname", "Cirino");
	    request.addParameter("email", sb.toString() + "." + "@studenti.unisa.it");
	    request.addParameter("sex", "M");
	    request.addParameter("password", "password");
	    request.addParameter("flag", "1");
	    servlet.doPost(request, response);
	  } 

}
