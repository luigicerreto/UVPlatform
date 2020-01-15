package test;

import static org.junit.jupiter.api.Assertions.assertEquals;


import controller.ServletLogin;

import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class ServletLoginTest extends Mockito {
  private ServletLogin servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  /**
   * Before.
   */
  @Before
  public void setUp() {
    servlet = new ServletLogin();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }

  @Test
  public void testLoginAdmin() throws ServletException, IOException {
    request.addParameter("email", "fferrucci@unisa.it");
    request.addParameter("password", "password");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  @Test
  public void testLoginCompany() throws ServletException, IOException {
    request.addParameter("email", "info@kineton.it");
    request.addParameter("password", "password");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testLoginTeacher() throws ServletException, IOException {
    request.addParameter("email", "rdeprisco@unisa.it");
    request.addParameter("password", "password");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  @Test
  public void testLoginFail2() throws ServletException, IOException {
    request.addParameter("email", "r.dipalma20@studenti.unisa.it");
    request.addParameter("password", "password");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  
  @Test
  public void testLoginStudent() throws ServletException, IOException {
    request.addParameter("email", "a.prova@studenti.unisa.it");
    request.addParameter("password", "password");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testLoginFail() throws ServletException, IOException {
    request.addParameter("email", "a.prova@studenti.unisa.it");
    request.addParameter("password", "passwordsbagliata");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testLoginSecretary() throws ServletException, IOException {
    request.addParameter("email", "segreteria@unisa.it");
    request.addParameter("password", "password");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
  
  @Test
  public void testLoginErrorType() throws ServletException, IOException {
    request.addParameter("email", "loginerror@studenti.unisa.it");
    request.addParameter("password", "password");
    request.addParameter("flag", "1");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }

  @Test
  public void testLoginErrorFlag() throws ServletException, IOException {
    request.addParameter("email", "loginerror@studenti.unisa.it");
    request.addParameter("password", "password");
    request.addParameter("flag", "133");
    servlet.doPost(request, response);
    assertEquals("json", response.getContentType());
  }
}
