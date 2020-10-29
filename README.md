# SpringEvents
spring events example


Eventos en Spring, para el diseño de aplicaciones más flexibles y desacopladas.


Una de las características del Patrón Observer es que permite notificar el cambio de estado entre objetos, esta es una excelente práctica si queremos desacoplar los componentes de una aplicación o mantener una comunicación centralizada entre ellos. Los eventos en Spring son un ejemplo de la implementación de este patrón, en este artículo abordo un ejemplo práctico muy simple de cómo publicar eventos en Spring.

Existen dos componentes fundamentales para el manejo de eventos en Spring:

EventListener:

Es el componente encargado de estar a la escucha de la publicación de eventos y hacer efectiva su ejecución.

EventPublisher:

Es el componente encargado de publicar o desencadenar el evento seleccionado.

Esta comunicación se hace efectiva en Spring gracias al ApplicationContext que funciona como bróker para el envío de eventos.

A continuación un ejemplo práctico de la creación de un evento y su publicación:

Dependencias:

      <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <optional>true</optional>
    </dependency>

    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

spring-boot-devtools es utilizada para el hot reload de los cambios, pero no es necesaria tenerla instalada.

EventListener:

      @Component
      public class EventListener implements ApplicationListener{

        @Override
        public void onApplicationEvent(ApplicationEvent event) {

          System.out.println(event.toString());
        }
      }

Evento:

      public class CustomEvent extends ApplicationEvent {

          public CustomEvent(Object source) {
              super(source);
          }
          public String toString(){

              return "##### Hola desde el customEvent #####";     
          }
      }

Clase controladora e implementación del EventPublisher:

        @RestController
        @RequestMapping("/events")
        public class eventController {

            @Autowired
            private ApplicationEventPublisher publisher;

            @GetMapping(value="/event1")
            public String event1() {

                CustomEvent event = new CustomEvent(this);

                publisher.publishEvent(event);

                return "hello from method 1";
            }

        }

La inyeccción de:

     @Autowired
        private ApplicationEventPublisher publisher;

Nos permite la llamada al método que ejecuta la publicación del evento del cual creamos una instancia de forma previa:

    CustomEvent event = new CustomEvent(this);
    publisher.publishEvent(event);

El resultado de la implementación anterior es que si accedemos a la dirección:

    http://localhost:8080/events/event1

y revisamos nuestra consola, podemos ver el mensaje que devuelve el método toString implementado en nuestro evento:

      public String toString(){

          return "##### Hola desde el customEvent #####";     
      }


