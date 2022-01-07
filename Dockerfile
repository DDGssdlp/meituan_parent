# 该镜像需要依赖的基础镜像
FROM openjdk:8
# 将当前目录下的jar包复制到docker容器的/目录下
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
# 指定docker容器启动时运行jar包
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]