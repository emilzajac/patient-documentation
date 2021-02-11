FROM postgres:12.3

COPY init/init-user-db.sh /docker-entrypoint-initdb.d/init-user-db.sh

EXPOSE 5432
CMD ["postgres"]
