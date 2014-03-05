package com.iokays.article.velocity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:rest-servlet.xml"})
public class VelocityTest {
    @Test
    public void test() throws IOException {
        Properties p = new Properties();
//		p.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        p.load(this.getClass().getResourceAsStream("/velocity.properties"));
        Velocity.init(p);
        VelocityContext context = new VelocityContext();

        ArrayList<String> list = new ArrayList<String>();

        list.add("HelloVelocity 1");
        list.add("HelloVelocity 2");
        list.add("HelloVelocity 3");
        list.add("HelloVelocity 4");

        context.put("list", list);
        context.put("title", "小仓优子");
        context.put("context", "小仓优子的相片");
        Template template = Velocity.getTemplate("com/iokays/article/vm/article.vm", "UTF-8");

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                System.out));

        template.merge(context, writer);

        writer.flush();
        writer.close();
    }
}
