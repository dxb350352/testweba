package sysproperty;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("user.name", "用户的账户名称");
        map.put("user.home", "用户的主目录");
        map.put("user.dir", "用户的当前工作目录");
        map.put("os.name", "操作系统的名称");
        map.put("os.arch", "操作系统的架构");
        map.put("os.version", "操作系统的版本");
        map.put("file.separator", "文件分隔符(在 UNIX 系统中是\"/\")");
        map.put("path.separator", "路径分隔符(在 UNIX 系统中是\":\")");
        map.put("line.separator", "行分隔符(在 UNIX 系统中是\"/n\")");
        map.put("java.version", "Java 运行时环境版本");
        map.put("java.vendor", "Java 运行时环境供应商");
        map.put("java.vendor.url", "Java 供应商的 URL");
        map.put("java.home", "Java 安装目录");
        map.put("java.vm.specification.version", "Java 虚拟机规范版本");
        map.put("java.vm.specification.vendor", "Java 虚拟机规范供应商");
        map.put("java.vm.specification.name", "Java 虚拟机规范名称");
        map.put("java.vm.version", "Java 虚拟机实现版本");
        map.put("java.vm.vendor", "Java 虚拟机实现供应商");
        map.put("java.vm.name", "Java 虚拟机实现名称");
        map.put("java.specification.version", "Java 运行时环境规范版本");
        map.put("java.specification.vendor", "Java 运行时环境规范供应商");
        map.put("java.specification.name", "Java 运行时环境规范名称");
        map.put("java.class.version", "Java 类格式版本号");
        map.put("java.class.path", "Java 类路径");
        map.put("java.library.path", "加载库时搜索的路径列表");
        map.put("java.io.tmpdir", "默认的临时文件路径");
        map.put("java.compiler", "要使用的 JIT 编译器的名称");
        map.put("java.ext.dirs", "一个或多个扩展目录的路径");
        map.keySet().parallelStream().forEach(key -> {
            System.out.println(key + ":" + map.get(key) + ":" + System.getProperty(key));
        });
    }
}
