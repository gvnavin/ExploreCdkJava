 package com.gvnavin.infra.explorecdk;

 import software.amazon.awscdk.App;
 import software.amazon.awscdk.assertions.Template;

 import java.io.IOException;

 public class Test {

     @org.junit.jupiter.api.Test
     public void testStack() throws IOException {
         App app = new App();
         ServiceStack stack = new ServiceStack(app, "test");
         Template template = Template.fromStack(stack);
         //todo: test the code changes
     }
 }
