package uy.com.proitc.servlet;

import static uy.com.proitc.servlet.ProductDisplayFilter.FILTER_NAME;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = FILTER_NAME, urlPatterns = "/*")
public class ProductDisplayFilter extends HttpFilter {

  public static final String FILTER_NAME = "ProductDisplayFilter";
  private static final Logger log = Logger.getLogger(ProductDisplayFilter.class.getName());

  @Override
  protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
      throws IOException, ServletException {
    log.info("Filter in action");
    res.getWriter().println("Filter content");
    chain.doFilter(req, res);
  }
}
