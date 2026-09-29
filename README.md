# S09 - Integración de Slack con Jenkins (Calculadora)

Proyecto Maven con una calculadora básica y pruebas JUnit 5. El `Jenkinsfile` ejecuta `mvn clean test` y notifica el resultado (SUCCESS / FAILURE) al canal `#notificaciones-jenkins` de Slack.

## Estructura

```
00_PSW_ValeryChumpitaz2
└── S09-Slack
    ├── Jenkinsfile
    ├── pom.xml
    └── src
        ├── main/java/com/psw/calculadora/Calculadora.java
        └── test/java/com/psw/calculadora/CalculadoraTest.java
```

## Ejecutar pruebas localmente

```
mvn clean test
```

## Configuración de Slack en Jenkins

1. Slack: crear el workspace (ej. Equipo QA) y el canal `#notificaciones-jenkins`.
2. Jenkins: Manage Jenkins > Plugins > instalar **Slack Notification**.
3. Slack: crear una app/integración Jenkins CI y copiar el token.
4. Jenkins: Manage Jenkins > Credentials > agregar el token como **Secret Text** (nunca en el Jenkinsfile ni en el repositorio).
5. Jenkins: Manage Jenkins > System > Slack: indicar Workspace, Credential ID y canal por defecto `#notificaciones-jenkins`. Probar con *Test Connection*.
6. Crear un job Pipeline apuntando al `Jenkinsfile` de este repositorio y ejecutar **Build Now**.

## Demostraciones

- **Éxito:** Build Now con todas las pruebas pasando → mensaje `Build SUCCESS` en Slack.
- **Fallo:** cambiar una aserción (ej. `assertEquals(9.0, calc.sumar(5, 3))`), commit y push, Build Now → mensaje `Build FAILURE` en Slack.
