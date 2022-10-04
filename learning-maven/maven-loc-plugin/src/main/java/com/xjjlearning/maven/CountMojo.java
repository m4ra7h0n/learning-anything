package com.xjjlearning.maven;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import org.apache.maven.model.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Goal which touches a timestamp file.
 * @deprecated Don't use!
 */
@Mojo( name = "count", defaultPhase = LifecyclePhase.PROCESS_SOURCES )
public class CountMojo
    extends AbstractMojo
{
    private static final String[] INCLUDES_DEFAULT = {"java", "xml", "properties", "sql"};

    @Parameter( defaultValue = "${project.basedir}")
    private File basedir;

    @Parameter( defaultValue = "${project.basedir}/src/main/java")
    private File sourceDirectory;

    @Parameter( defaultValue = "${project.basedir}/src/test/java")
    private File testSourceDirectory;

    private String[] includes;

    public void execute()
        throws MojoExecutionException
    {
        getLog().info("my plugin !Q!");
        getLog().info(basedir.toString());
        getLog().info("my plugin !Q!");
        if (includes == null || includes.length == 0) {
            includes = INCLUDES_DEFAULT;
        }

        try {
            countDir(sourceDirectory);
            countDir(testSourceDirectory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void countDir(File dir) throws IOException {
        if (!dir.exists()) {
            return;
        }
        ArrayList<File> collected = new ArrayList<File>();
        collectFiles(collected, dir);
        int lines = 0;
        for (File sourceFile : collected) {
            lines += countLine(sourceFile);
        }
        String path = dir.getAbsolutePath().substring(basedir.getAbsolutePath().length());
        getLog().info(path + ": " + lines + " lines of code in " + collected.size() + " files");
    }

    private void collectFiles(List<File> collected, File file){
        if (file.isFile()) {
            for (String include : includes) {
                if (file.getName().endsWith("." + include)) {
                    collected.add(file);
                    break;
                }
            }
        } else {
            for (File sub : file.listFiles()) {
                collectFiles(collected, sub);
            }
        }
    }

    private int countLine(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int line = 0;
        try {
            while (reader.ready()) {
                reader.readLine();
                line++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return line;
    }
}
