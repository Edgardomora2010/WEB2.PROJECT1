// PAQUETES
package tec.nextshop_project_1;

// IMPORTACIÓN DE LIBRERÍAS
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tec.nextshop_project_1.interceptor.AuthenticationInterceptor;
import tec.nextshop_project_1.interceptor.TimingInterceptor;

/**
 * Clase de arranque de la aplicación
 */
@SpringBootApplication
public class NextShopProject1Application implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;
    private final TimingInterceptor timingInterceptor;

    /**
     * Constructor de clase con inyección de dependencias:
     * @param authenticationInterceptor
     * @param timingInterceptor
     */
    public NextShopProject1Application(AuthenticationInterceptor authenticationInterceptor, TimingInterceptor timingInterceptor) {
        this.authenticationInterceptor = authenticationInterceptor;
        this.timingInterceptor = timingInterceptor;
    }

    public static void main(String[] args) {
        SpringApplication.run(NextShopProject1Application.class, args);
    }

    /**
     * Configura y registra los interceptores que serán aplicados a todas
     * las rutas de la aplicación.
     * Permite ejecutar lógica de autenticación y medición de tiempo de
     * ejecución antes y después de procesar las solicitudes HTTP.
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(timingInterceptor)
                .addPathPatterns("/**");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

}
