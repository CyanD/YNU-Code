/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.22
 * Generated at: 2014-05-08 02:42:25 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class xlogin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write(" \r\n");
      out.write("    \r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("    <title>客户管理系统登陆</title>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery-1.10.2.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/bootstrap.min.js\"></script>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/bootstrap.min.css\"></link>\r\n");
      out.write("\t<link rel=\"icon\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/favicon.ico\" type=\"image/x-icon\" /> \r\n");
      out.write("    <link rel=\"shortcut icon\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/favicon.ico\" type=\"image/x-icon\" /> \r\n");
      out.write("     <link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/all.css\" type=\"text/css\"></link>\r\n");
      out.write("     <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/css/all.css\" />\r\n");
      out.write("     <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/Scripts/AC_RunActiveContent.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write(".centerAlign {\r\n");
      out.write("\tmargin-top: 12%;\r\n");
      out.write("\tmargin-right: auto;\r\n");
      out.write("\tmargin-bottom: auto;\r\n");
      out.write("\tmargin-left: auto;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".input_width{\r\n");
      out.write("  width:80%;\r\n");
      out.write("}\r\n");
      out.write(".sizeControl{\r\n");
      out.write("\twidth: 25%;\r\n");
      out.write("\theight: 20%;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".marginLR{\r\n");
      out.write("\tpadding-left: 10px;\r\n");
      out.write("\tpadding-right: 10px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".floatRight{\r\n");
      out.write("\tfloat: right;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".transparent{\r\n");
      out.write("\topacity: 0.7;\r\n");
      out.write("\tfilter:Alpha(Opacity='70');\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".non_transparent{\r\n");
      out.write("\topacity: 1;\r\n");
      out.write("\tfilter:Alpha(Opacity='100');\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("body {\r\n");
      out.write("\tbackground-attachment: fixed;\r\n");
      out.write("\tbackground-repeat: no-repeat;\r\n");
      out.write("\tbackground-position: center;\r\n");
      out.write("\tbackground-size:100% 100%;\r\n");
      out.write("\tbackground-image: url(images/LoginBackground.png);\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write(" <script type=\"text/javascript\">\r\n");
      out.write("     function flogin(text,e)\r\n");
      out.write("            {     \r\n");
      out.write("               \r\n");
      out.write("                    form.submit();\r\n");
      out.write("                   \r\n");
      out.write("              \r\n");
      out.write("            }\r\n");
      out.write("\t\r\n");
      out.write("</script>  \r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form id=\"form\" method=\"post\" name=\"login\" action=\"sys/xlogin.do\">\r\n");
      out.write("<div class=\"sizeControl centerAlign transparent\">\r\n");
      out.write("\t<div class=\"panel panel-primary\">\r\n");
      out.write("\t\t<div class=\"panel-heading\">系统登陆</div>\r\n");
      out.write("  \t\t<div class=\"panel-body\">\r\n");
      out.write("    \t\t<form class=\"form-horizontal\" role=\"form\" onSubmit=\"return false;\">\r\n");
      out.write("  \t\t\t\t<div class=\"form-group marginLR\">\r\n");
      out.write("    \t\t\t\t<label class=\"sr-only\" for=\"exampleInputFile\">userName</label>\r\n");
      out.write("   \t\t\t \t\t<input type=\"text\" class=\"form-control non_transparent\" name=\"user.userName\"  id=\"USERNAME\" placeholder=\"Enter userName\">\r\n");
      out.write("  \t\t\t\t</div>\r\n");
      out.write("  \t\t\t\t<div class=\"form-group marginLR\">\r\n");
      out.write("    \t\t\t\t<label class=\"sr-only\" for=\"exampleInputFile\">Password</label>\r\n");
      out.write("    \t\t\t\t<input type=\"password\" class=\"form-control non_transparent\" name=\"user.password\"  id=\"PASSWORD\" placeholder=\"Password\">\r\n");
      out.write("  \t\t\t\t</div>\t\r\n");
      out.write("  \t\t\t\t<div class = \"form-group marginLR floatRight\"><img id=\"codeImg\" src=\"\" width=\"65\" height=\"30\"  onclick=\"changeValidateCode()\"/> </div>\r\n");
      out.write("  \t\t\t\t<button type=\"submit\"  class=\"btn btn-primary marginLR non_transparent\"  onclick=\"javascript:submit()\" onkeypress=\"flogin(this,event)\">&nbsp;&nbsp;&nbsp;&nbsp;Login&nbsp;&nbsp;&nbsp;&nbsp;</button>\r\n");
      out.write("\t\t\t\t<div class=\"checkbox floatRight marginLR\">\r\n");
      out.write("    \t\t\t\t<label>\r\n");
      out.write("      \t\t\t\t\t<input type=\"checkbox\"> Remember me\r\n");
      out.write("    \t\t\t\t</label>\r\n");
      out.write("  \t\t\t\t</div>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("  \t\t</div>\t\t\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}