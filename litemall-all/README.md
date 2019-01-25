部署方案中设计了一种单主机单服务方案， 也就是说两个后台服务和静态文件都部署在一个Spring Boot可执行jar包中。

查看litemall-all模块，代码仅仅只有一个Application类。

实际的原理是litemall-all模块内的pom.xml文件：

打包方式是jar，因此最后会打包可执行jar格式；

对litemall-wx-api模块和litemall-admin-api模块依赖，

因此打包时会作为依赖库而打包到litemall-all模块的输出中；

使用copy-resources插件，在打包时把litemall-admin模块的dist 文件夹拷贝到litemall-all模块的static文件夹中；而这个文件夹 正是Spring Boot应用的默认静态文件路径。

注意：

这个插件只是简单的拷贝操作；因此开发者应该在打包litemall-all 之前确保先编译litemall-admin模块得到最终静态文件。
