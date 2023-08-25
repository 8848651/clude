package com.contest.xczx_plus_content_service.content.jobhandler;

import com.contest.xczx_plus_content_model.content.Pto.FreemarkerModelDto;
import com.contest.xczx_plus_content_service.content.service.Interface.FreemarkerService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

@Component
public class PageStatization {

    @Autowired
    private FreemarkerService freemarkerService;

    public File Statization(Long id) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.getVersion());
        String classpath = this.getClass().getResource("/").getPath();
        File file1 = new File(classpath + "/templates/");
        configuration.setDirectoryForTemplateLoading(file1);
        configuration.setDefaultEncoding("utf-8");
        //模板
        Template template = configuration.getTemplate("course_template.ftl");
        //数据
        FreemarkerModelDto freemarkerselect = freemarkerService.Freemarkerselect(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("model",freemarkerselect);
        //模板,数据
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        InputStream inputStream = IOUtils.toInputStream(html, "utf-8");
        File file2 = File.createTempFile("Html", ".html");
        FileOutputStream outputStream = new FileOutputStream(file2);
        IOUtils.copy(inputStream,outputStream);
        inputStream.close();
        outputStream.close();
        return file2;
    }
}
