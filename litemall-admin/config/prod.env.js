module.exports = {
  // 上线阶段，当开发者运行命令cnpm run build:prod，这里就会采用prod.env.js配置文件。
	NODE_ENV: '"production"',
	ENV_CONFIG: '"prod"',
    BASE_API: '"https://www.example.com/admin"'
}
