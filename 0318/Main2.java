package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Main2 {
    public static void main(String[] args) throws ParseException {
        // 원래의 사용은 이래요
        // Builder classPathBuilder = Option.builder("class-path");
        // classPathBuilder.hasArg();
        // classPathBuilder.desc("Class Path");
        // Option classPath = classPathBuilder.build();

        // 진화된 사용 방식
        // 1. 빌더라는 것을 하나 만들어서
        // 2. 옵션을 점점 추가해 나감.

        Options options = new Options(); // pom.xml의 라이브러리에서 가져왔음.
        // ----------------------------------------------------------------------

        // 클래스 패스에 대한 옵션을 만들고
        Option classPath = Option.builder("class-path")
                .hasArg()
                .desc("Class Path")
                .build();

        // 옵션 추가
        options.addOption(classPath);

        // ----------------------------------------------------------------------

        // 모듈에 대한 옵션을 만들고
        Option module = Option.builder("m")
                .longOpt("module")
                .hasArg()
                .desc("Modules")
                .build();

        // 옵션 추가
        options.addOption(module);

        // ----------------------------------------------------------------------

        // 그룹에 대한 옵션을 만들고
        Option group = Option.builder("g")
                .longOpt("Group")
                .hasArg()
                .desc("Group")
                .build();

        // 옵션 추가
        options.addOption(group);

        // ----------------------------------------------------------------------

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        System.out.println(cmd.getArgList());
    }
}