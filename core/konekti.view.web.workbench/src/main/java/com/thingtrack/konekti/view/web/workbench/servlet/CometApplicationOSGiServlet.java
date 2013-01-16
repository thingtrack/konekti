package com.thingtrack.konekti.view.web.workbench.servlet;
 
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.MalformedURLException;
 
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.vaadin.dontpush.server.DontPushOzoneWebApplicationContext;
import org.vaadin.dontpush.server.SocketCommunicationManager;
 
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.ApplicationOSGiServlet;
import com.vaadin.terminal.gwt.server.CommunicationManager;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Window;
 
@SuppressWarnings("serial")
public class CometApplicationOSGiServlet extends ApplicationOSGiServlet {
 
    private static final ThreadLocal<SocketCommunicationManager> activeManager = new ThreadLocal<SocketCommunicationManager>();
    private Class<? extends SocketCommunicationManager> communicationManagerClass;
 
    @SuppressWarnings("unchecked")
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        String communicationManagerClassName = servletConfig
                .getInitParameter("communicationManagerClass");
        if (communicationManagerClassName != null) {
            try {
                this.communicationManagerClass = (Class<SocketCommunicationManager>) Class
                        .forName(communicationManagerClassName);
            } catch (ClassNotFoundException e) {
                this.communicationManagerClass = null;
            }
        }
    }
 
    @Override
    protected WebApplicationContext getApplicationContext(HttpSession session) {
        WebApplicationContext cx = (WebApplicationContext) session
                .getAttribute(WebApplicationContext.class.getName());
        if (cx == null) {
            cx = new DontPushOzoneWebApplicationContext(session,
                    this.communicationManagerClass);
            session.setAttribute(WebApplicationContext.class.getName(), cx);
        }
        return cx;
    }
 
    @Override
    protected boolean handleURI(CommunicationManager applicationManager,
            Window window, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
 
        boolean handled;
        try {
            activeManager.set((SocketCommunicationManager) applicationManager);
            handled = super.handleURI(applicationManager, window, request,
                    response);
        } catch (IOException ioe) {
            activeManager.remove();
            throw ioe;
        }
        if (handled) // writeAjaxPage not called if super.handleURI(...) returns true
            activeManager.remove();
        return handled;
    }
 
    @Override
    protected void writeAjaxPage(HttpServletRequest request,
            HttpServletResponse response, Window window, Application application)
            throws IOException, MalformedURLException, ServletException {
 
        try {
            response.addCookie(new Cookie("OZONE_CM_ID", activeManager.get()
                    .getId()));
            super.writeAjaxPage(request, response, window, application);
        } finally {
 
            activeManager.remove();
        }
    }
 
    @Override
    protected void writeAjaxPageHtmlHeader(BufferedWriter page, String title,
            String themeUri, HttpServletRequest request) throws IOException {
 
        super.writeAjaxPageHtmlHeader(page, title, themeUri, request);
 
        
        //DontPush Ozone Layer script
        String cGuardTimeout = getApplicationProperty("connectionGuardTimeout");
        
        if (cGuardTimeout != null) {
            int to = Integer.parseInt(cGuardTimeout);
            page.write("<script type=\"text/javascript\">ozonelayerConnectionGuardTimeout = "
                    + to + ";</script>");
        }
     }
 
}
