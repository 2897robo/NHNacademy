package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Option.Builder;

public class Main {
    public static void main(String[] args) throws ParseException {
        // create Options object
        Options options = new Options();

        // add t option
        options.addOption("v", "version", false, "print the version");

        Builder classPathBuilder = Option.builder("class-path");
        classPathBuilder.hasArg();
        classPathBuilder.desc("Class Path");
        Option classPath1 = classPathBuilder.build();

        Option classPath = Option.builder("classpath")
                        .longOpt("class-path")
                       .hasArg()
                       .desc("Class Path")
                       .build();
        options.addOption(classPath);

        Option module = Option.builder("m")
        .longOpt("module")
        .hasArg()
        .desc("Modules")
        .build();

        options.addOption(module);
        
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        System.out.println(cmd.getArgList());
    }
}