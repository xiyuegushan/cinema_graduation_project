FROM mysql:8.0.27

MAINTAINER lengleng(wangiegie@gmail.com)

ENV TZ=Asia/Shanghai

RUN ln -sf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY ./cinema.sql /docker-entrypoint-initdb.d

COPY ./cinema_codegen.sql /docker-entrypoint-initdb.d

COPY ./cinema_config.sql /docker-entrypoint-initdb.d

COPY ./cinema_job.sql /docker-entrypoint-initdb.d
