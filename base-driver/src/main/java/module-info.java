module BaseDriver {
    requires org.seleniumhq.selenium.api;
    requires org.seleniumhq.selenium.chrome_driver;
    requires org.seleniumhq.selenium.support;
    requires org.junit.jupiter.api;
    requires java.net.http;
    requires com.google.gson;
    requires com.fasterxml.jackson.databind;
    requires io.github.bonigarcia.webdrivermanager;
    exports com.base.web_driver;
    exports com.base.api_client;
}