/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2013, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.itutorial;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CodeController {
    
    @Autowired private ServletContext servletContext;

    @RequestMapping(value = "/resources/{type}/{resource}.{extension}", method = RequestMethod.GET)
    public void code(
            @PathVariable("type") String type,
            @PathVariable("resource") String resource,
            @PathVariable("extension") String extension,
            HttpServletResponse response) throws IOException {
        String resourcePath = "/WEB-INF/" + type + "/" + resource + "." + extension;
        InputStream resourceStream = servletContext.getResourceAsStream(resourcePath);
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        FileCopyUtils.copy(resourceStream, response.getOutputStream());
    }
}