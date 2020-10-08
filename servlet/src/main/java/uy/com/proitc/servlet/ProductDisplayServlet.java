package uy.com.proitc.servlet;

import static uy.com.proitc.servlet.ProductDisplayServlet.INIT_KEY;
import static uy.com.proitc.servlet.ProductDisplayServlet.INIT_VALUE;
import static uy.com.proitc.servlet.ProductDisplayServlet.PRODUCT_SERVLET_NAME;

import java.io.IOException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = PRODUCT_SERVLET_NAME, urlPatterns = "/products",
    initParams = @WebInitParam(name = INIT_KEY, value = INIT_VALUE))
public class ProductDisplayServlet extends HttpServlet {

  private static final Logger log = Logger.getLogger(ProductDisplayServlet.class.getName());
  public static final String INIT_KEY = "service";
  public static final String INIT_VALUE = "product";
  public static final String PRODUCT_SERVLET_NAME = "ProductDisplay";
  private final ProductService productService;

  @Inject
  public ProductDisplayServlet(ProductService productService) {
    this.productService = productService;
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    log.info(String.format("Method: %s", req.getMethod()));
    doGet(req, resp);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    resp.setContentType("text/plain;charset=UTF-8");
    var writer = resp.getWriter();
    writer.println(productService.findAll());
  }
}
