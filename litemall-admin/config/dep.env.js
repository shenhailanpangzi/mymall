module.exports = {
  // 部署阶段，当开发者运行命令cnpm run build:dep，这里就会采用dep.env.js配置文件
	NODE_ENV: '"production"',
  ENV_CONFIG: '"dep"',
  BASE_API: '"http://122.152.206.172:8080/admin"'
}
